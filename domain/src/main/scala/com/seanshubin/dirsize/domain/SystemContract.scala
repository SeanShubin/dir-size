package com.seanshubin.dirsize.domain

import java.io._
import java.nio.channels.Channel
import java.util.{Map, Properties}

trait SystemContract {
  def in: InputStream

  def out: PrintStream

  def err: PrintStream

  def setIn(in: InputStream)

  def setOut(out: PrintStream)

  def setErr(err: PrintStream)

  def console: Console

  def inheritedChannel: Channel

  def setSecurityManager(s: SecurityManager)

  def getSecurityManager: SecurityManager

  def currentTimeMillis: Long

  def nanoTime: Long

  def arraycopy(src: AnyRef, srcPos: Int, dest: AnyRef, destPos: Int, length: Int)

  def identityHashCode(x: AnyRef): Int

  def getProperties: Properties

  def lineSeparator: String

  def setProperties(props: Properties)

  def getProperty(key: String): String

  def getProperty(key: String, default: String): String

  def setProperty(key: String, value: String): String

  def clearProperty(key: String): String

  def getenv(name: String): String

  def getenv: Map[String, String]

  def exit(status: Int)

  def gc()

  def runFinalization()

  def load(filename: String)

  def loadLibrary(libname: String)

  def mapLibraryName(libname: String): String
}
