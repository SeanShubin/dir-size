package com.seanshubin.dirsize.console

import java.nio.charset.{Charset, StandardCharsets}
import java.nio.file.Path

import com.seanshubin.dirsize.domain._

trait RunnerDependencyInjection {
  def targetDir:Path
  def reportPath:Path
  def minSize:Long
  val charset:Charset = StandardCharsets.UTF_8
  val emit:String => Unit = println
  val files:FilesContract = FilesDelegate
  val notifications:Notifications = new LineEmittingNotifications(emit, reportPath, minSize, charset, files)
  val fireReportEvent:Report => Unit = notifications.notifyReport
  val fireDurationEvent:(Long, Long) => Unit = notifications.notifyDuration
  val lookingAt:(Path, Int) => Unit = notifications.lookingAt
  val reportBuilder:ReportBuilder = new TreeReportBuilder(files, lookingAt)
  val system:SystemContract = SystemDelegate
  val runner:Runnable = new DirSizeRunner(targetDir, reportBuilder, fireReportEvent, fireDurationEvent, system)
}
