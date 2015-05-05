package com.yarenty.functions

import java.io.{File, PrintWriter}
import java.util.Date

/**
 * Created by yarenty on 05/05/15.
 */
object NewControl extends App {

  /**
   * op is getting one double as argument and returning double as output
   *
   * @param op
   * @param x
   * @return
   */
  def twice(op: Double => Double, x: Double) = op(op(x))


  println(twice(_ + 1, 5)) //7

  println(twice(_ + 2, 10))

  //14?
  // op is getting one double as argument and returning double as output


  def withPrintWriter(file: File, op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }


  withPrintWriter(new File("date.txt"), writer => writer.println(new Date))

}
