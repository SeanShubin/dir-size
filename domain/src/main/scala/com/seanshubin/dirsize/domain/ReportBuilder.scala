package com.seanshubin.dirsize.domain

import java.nio.file.Path

trait ReportBuilder {
  def build(targetDir:Path, depth:Int):Report
}
