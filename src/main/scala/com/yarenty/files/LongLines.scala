package com.yarenty.files

import scala.io.Source

/**
 * Created by yarenty on 02/05/15.
 */
object LongLines {

  def processFile(filename: String, width: Int): Unit = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }

  private def processLine(filename: String, width: Int, line: String): Unit = {
    if (line.length > width)
      println(filename + ": " + line.trim)
  }

}


// same but local functions!!
object LongLines2 {

  def processFile(filename: String, width: Int): Unit = {

    //doesnt it look simpler?
    def processLine(line: String): Unit = {
      if (line.length > width)
        println(filename + ": " + line.trim)
    }

    val source = Source.fromFile(filename)

    for (line <- source.getLines())
      processLine(line)
  }

}


object FindLongLines {
  def main(args: Array[String]) {
    val width = 10
    println("Object Oriented::")
    LongLines.processFile("README.md", width)

    println("Functional - local functions::")
    LongLines2.processFile("README.md", width)
  }
}