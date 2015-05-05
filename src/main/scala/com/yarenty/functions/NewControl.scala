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


  def withPrintWriter2(file: File)(op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  //hmmm, . .. this looks cooler ;-)
  withPrintWriter2(new File("date.txt")) {
    writer => writer.println(new Date + "\nsecond time")
  }


  var assertionEnabled = true

  def myAssert(predicate: () => Boolean) =
    if (assertionEnabled && !predicate())
      throw new AssertionError

  println(myAssert(() => 5 > 3))


  def byNameAssert(predicate: => Boolean) =
    if (assertionEnabled && !predicate)
      throw new AssertionError


  println(byNameAssert(5 < 3))


}
