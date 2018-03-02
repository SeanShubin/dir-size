package com.seanshubin.dirsize.console
import java.nio.file.{Path, Paths}

import com.seanshubin.dirsize.domain.ConfigurationRunner

trait ConfigurationDependencyInjection {
  def commandLineArguments:Array[String]
  val createRunner:(Path, Path, Long)=>Runnable = (theTargetDir, theReportPath, theMinSize) => new RunnerDependencyInjection {
    override def targetDir: Path = theTargetDir

    override def reportPath: Path = theReportPath

    override def minSize: Long = theMinSize
  }.runner
  val runner:Runnable = new ConfigurationRunner(commandLineArguments, createRunner)
}
