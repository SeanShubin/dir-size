package com.seanshubin.dirsize.domain

import scala.collection.mutable.ArrayBuffer

sealed abstract case class FileType(name: String) {
  FileType.valuesBuffer += this
}

object FileType {
  private val valuesBuffer = new ArrayBuffer[FileType]
  lazy val values: Seq[FileType] = valuesBuffer
  val RegularFile: FileType = new FileType("file") {}
  val Directory: FileType = new FileType("directory") {}
}