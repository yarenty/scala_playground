package com.yarenty.trycatches

import java.io.{FileNotFoundException, FileReader, IOException}

/**
 * Created by yarenty on 01/05/15.
 */
object Throweres extends App {

  def isEven(n: Int) =
    if (n % 2 == 0)
      true
    else
      throw new IllegalArgumentException(" n must be even")

  try {

    println(isEven(2))
    println(isEven(3))

  } catch {
    case ex: Exception => println(ex)
  }



  // check loan pattern -> scala way of opening / performing and then closing resources
  try {
    val f = new FileReader("not_existing.txt")
    f.read();
    f.close();
  } catch {
    case ex: FileNotFoundException => println("NOT found")
    case ex: IOException => println("internal IO exception")
  } finally {
    println("I must see this")
  }


  //YIELDING
  //java style - will always return final value
  def f(): Int = try {
    return 1
  } finally {
    return 2
  }

  //scala style - if no error - value inside will be return
  // on error still last value inside will be returned however there will be call to final
  def g(): Int = try {
    1
  } finally {
    2
  }

  // and here is => returned is: 1 or 0 however there is  always call to finally
  def g2(x: Int): Int = {
    try {
      if (x == 1)
        1
      else
        throw new IllegalArgumentException(" nope ")
    } catch {
      case ex: IllegalArgumentException => println("there was exception"); 0
    } finally {
      println("was there call?")
      2
    }
  }

  println(f)
  println(g)
  println(g2(1))
  println(g2(4))



}
