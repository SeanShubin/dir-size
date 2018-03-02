package com.seanshubin.dirsize.domain

import java.nio.charset.Charset
import java.nio.file.Path

import scala.collection.JavaConverters._

class LineEmittingNotifications(emit: String => Unit,
                                reportPath: Path,
                                minSize:Long,
                                charset: Charset,
                                files:FilesContract) extends Notifications {
  override def notifyReport(report: Report): Unit = {
    val tableRows = report.rows.filter(_.size >= minSize).sorted(ReportRow.SizeDescendingPathAscending).map(reportRowToTableRow)
    val tableLines = TableUtil.createTable(tableRows)
    tableLines.foreach(emit)
    files.write(reportPath, tableLines.asJava, charset)
  }

  override def notifyDuration(start: Long, end: Long): Unit = {
    val duration = end - start
    val durationString = DurationFormat.MillisecondsFormat.format(duration)
    val message = s"took $durationString"
    emit(message)
  }

  override def lookingAt(path: Path, depth:Int): Unit = {
    if(depth < 4){
      emit(path.toString)
    }
  }

  private def reportRowToTableRow(reportRow: ReportRow): Seq[Any] = {
    val name = reportRow.path.toString
    val size = reportRow.size
    val fileType = reportRow.fileType
    Seq(name, fileType.name, size)
  }
}
