package com.seanshubin.dirsize.domain

import java.nio.file.Path

class DirSizeRunner(targetDir: Path,
                    reportBuilder:ReportBuilder,
                    fireReportEvent: Report => Unit,
                    fireDurationEvent:(Long, Long) => Unit,
                    system:SystemContract) extends Runnable {
  override def run(): Unit = {
    val startTime = system.currentTimeMillis
    val report = reportBuilder.build(targetDir, 0)
    fireReportEvent(report)
    val endTime = system.currentTimeMillis
    fireDurationEvent(startTime, endTime)
  }
}
