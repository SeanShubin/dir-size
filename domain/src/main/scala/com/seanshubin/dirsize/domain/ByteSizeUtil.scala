package com.seanshubin.dirsize.domain

import scala.annotation.tailrec

object ByteSizeUtil {
  private val InBytes = """(\d+)""".r
  private val InKiloBytes = """(\d+)\s*k""".r
  private val InMegaBytes = """(\d+)\s*m""".r
  private val InTeraBytes = """(\d+)\s*t""".r
  private val InPetaBytes = """(\d+)\s*p""".r
  private val InExaBytes = """(\d+)\s*e""".r
  private val InZettaBytes = """(\d+)\s*z""".r
  private val InYottaBytes = """(\d+)\s*y""".r
  def parse(original:String):Long = {
    val scrubbed = original.trim.toLowerCase()
    scrubbed match {
      case InYottaBytes(s) => scaleUp(s, 7)
      case InZettaBytes(s) => scaleUp(s, 6)
      case InExaBytes(s) => scaleUp(s, 5)
      case InPetaBytes(s) => scaleUp(s, 4)
      case InTeraBytes(s) => scaleUp(s, 3)
      case InMegaBytes(s) => scaleUp(s, 2)
      case InKiloBytes(s) => scaleUp(s, 1)
      case InBytes(s) => scaleUp(s, 0)
    }
  }
  private def scaleUp(s:String, power:Int):Long = {
    val x = s.toLong
    scaleUpRecursive(x, power)
  }
  @tailrec
  private def scaleUpRecursive(x:Long, power:Int):Long = {
    if(power < 1){
      x
    } else {
      scaleUpRecursive(x * 1024, power - 1)
    }
  }
}
