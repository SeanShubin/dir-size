package com.seanshubin.dirsize.domain

import java.nio.file.Path

case class ReportRow(path: Path, fileType:FileType, size: Long) {
  def tupleSizePath: (Long, String) = (size, path.toString)
}

object ReportRow {
  val SizeDescendingPathAscending: Ordering[ReportRow] = (x: ReportRow, y: ReportRow) => {
    Ordering.Tuple2(Ordering.Long.reverse, Ordering.String).compare(x.tupleSizePath, y.tupleSizePath)
  }
}