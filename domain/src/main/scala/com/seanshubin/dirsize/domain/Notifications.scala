package com.seanshubin.dirsize.domain

import java.nio.file.Path

trait Notifications {
  def notifyReport(report:Report):Unit
  def notifyDuration(start:Long, end:Long):Unit
  def lookingAt(path:Path, depth:Int):Unit
}
