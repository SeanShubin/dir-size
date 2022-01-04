package com.seanshubin.dirsize.domain

import java.nio.file.{FileSystemException, Path}
import java.util.stream.{Collectors, Stream}

import com.seanshubin.dirsize.domain.Report.{Branch, Leaf}

import scala.collection.JavaConverters._

class TreeReportBuilder(files: FilesContract,
                        accept:Path => Boolean,
                        lookingAt: (Path, Int) => Unit,
                        notDirectoryOrFile: Path => Unit,
                        unableToListDir: Path => Unit) extends ReportBuilder {
  override def build(target: Path, depth: Int): Report = {
    if(files.isSymbolicLink(target)){
      Leaf(target, FileType.SymbolicLink, size = 0)
    } else if (files.isDirectory(target)) {
      lookingAt(target, depth)
      val stream = listFilesNoThrow(target)
      val contents = stream.collect(Collectors.toList()).asScala.filter(fileOrDirectory).filter(accept)
      stream.close()
      val children = contents.map(x => build(x, depth + 1)).toIndexedSeq
      Branch(target, FileType.Directory, children)
    } else if (files.isRegularFile(target)) {
      val size = files.size(target)
      Leaf(target, FileType.RegularFile, size)
    } else {
      notDirectoryOrFile(target)
      Leaf(target, FileType.Unknown, size = 0)
    }
  }

  private def listFilesNoThrow(path: Path): Stream[Path] = {
    try {
      files.list(path)
    } catch {
      case _: FileSystemException =>
        unableToListDir(path)
        java.util.stream.Stream.empty()
    }
  }

  private def fileOrDirectory(path: Path): Boolean = files.isDirectory(path) || files.isRegularFile(path)
}
