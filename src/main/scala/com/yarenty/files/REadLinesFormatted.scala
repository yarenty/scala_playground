package com.yarenty.files

import scala.io.Source

/**
 * Created by yarenty on 29/04/15.
 */
object ReadLinesFormatted {


  def widthOfLines(s: String) = s.length.toString.length //first is real length say:23 then is length of number:2

  def main(args: Array[String]) {
    if (args.length > 0) {
      val lines = Source.fromFile(args(0)).getLines().toList

      //using vars - not best (not functional)
      var maxWidth = 0
      for (line <- lines) {
        maxWidth = maxWidth.max(widthOfLines(line))
      }

      //alternative  - quite functional
      val longestLine = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)
      val maxWidth2 = widthOfLines(longestLine)

      for (line <- lines) {
        val numSpaces = maxWidth - widthOfLines(line)
        val padding = " " * numSpaces
        println(padding + line.length + "|" + line)
      }

    } else {
      Console.err.println("Please enter a filename.")
    }
  }
}
