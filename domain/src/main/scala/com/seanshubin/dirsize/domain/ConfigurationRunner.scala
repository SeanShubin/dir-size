package com.seanshubin.dirsize.domain

import java.nio.file.{Path, Paths}

class ConfigurationRunner(commandLineArguments:Array[String], createRunner:(Path, Path, Long) => Runnable) extends Runnable {
  override def run(): Unit = {
    val targetDir = Paths.get(commandLineArguments(0))
    val reportPath = Paths.get(commandLineArguments(1))
    val minSize = ByteSizeUtil.parse(commandLineArguments(2))
    val runner = createRunner(targetDir, reportPath, minSize)
    runner.run()
  }
}
