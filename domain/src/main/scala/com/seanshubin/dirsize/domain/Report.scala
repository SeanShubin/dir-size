package com.seanshubin.dirsize.domain

import java.nio.file.Path

trait Report {
  def rows:Seq[ReportRow]
  def size:Long
}

object Report {
  case class Branch(path:Path, fileType:FileType, children:Seq[Report]) extends Report {
    override def rows: Seq[ReportRow] = {
      val head:ReportRow = ReportRow(path, fileType, size)
      val tail:Seq[ReportRow] = children.flatMap(_.rows)
      head +: tail
    }

    override def size: Long = {
      children.map(_.size).sum
    }
  }
  case class Leaf(path:Path, fileType:FileType, size:Long) extends Report {
    override def rows: Seq[ReportRow] = {
      Seq(ReportRow(path, fileType, size))
    }
  }
}
