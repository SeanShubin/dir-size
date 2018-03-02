package com.seanshubin.dirsize.console

object EntryPoint extends App {
  new ConfigurationDependencyInjection {
    override def commandLineArguments: Array[String] = args
  }.runner.run()
}
