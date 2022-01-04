package com.seanshubin.dirsize.domain

import java.nio.file.Path

object IgnoreLibrary extends (Path => Boolean) {
  override def apply(path: Path): Boolean = path.getFileName.toString != "Library"
}
