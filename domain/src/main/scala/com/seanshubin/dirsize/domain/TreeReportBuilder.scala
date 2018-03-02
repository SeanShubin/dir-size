package com.seanshubin.dirsize.domain

import java.nio.file.Path
import java.util.stream.Collectors

import com.seanshubin.dirsize.domain.Report.{Branch, Leaf}

import scala.collection.JavaConverters._

class TreeReportBuilder(files: FilesContract, lookingAt:(Path, Int) => Unit) extends ReportBuilder {
  override def build(target: Path, depth:Int): Report = {
    if (files.isDirectory(target)) {
      lookingAt(target, depth)
      val stream = files.list(target)
      val contents = stream.collect(Collectors.toList()).asScala.filter(fileOrDirectory)
      stream.close()
      val children = contents.map(x => build(x, depth+1)).toIndexedSeq
      Branch(target, FileType.Directory, children)
    } else if (files.isRegularFile(target)) {
      val size = files.size(target)
      Leaf(target, FileType.RegularFile, size)
    } else {
      ???
    }
  }

  private def fileOrDirectory(path: Path): Boolean = files.isDirectory(path) || files.isRegularFile(path)
}
