package com.yarenty.files

import scala.io.Source

/**
 * Created by yarenty on 29/04/15.
 */
object ReadLines {


  def main(args: Array[String]) {
    if (args.length > 0) {
      for (line <- Source.fromFile(args(0)).getLines())
        println(line.length + "> " + line)
    } else {
      Console.err.println("Please enter a filename.")
    }
  }
}
