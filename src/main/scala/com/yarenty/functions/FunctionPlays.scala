package com.yarenty.functions

/**
 * Created by yarenty on 02/05/15.
 */
object FunctionPlays extends App {

  val increase = (x: Int) => x + 66

  println(" and the number is: ")
  println(increase(600))

  println("foreach is function on all collections:")
  val someNumbers = List(-1, 20, -43, 43, 65)
  someNumbers.foreach(println)


  println("same with filter where you can apply function:")
  someNumbers.filter((s: Int) => s > 0).foreach(println)

  println(" placeholder syntax: _ > 0")
  someNumbers.filter(_ > 0).foreach(println)

  println(" lets make multiply _*_ :")
  val multiply = (_: Int) * (_: Int) //first param then second param!

  println("2*6 =" + multiply(2, 6))

  def sum(a: Int, b: Int, c: Int) = a + b + c

  println("sum(1,2,3)")
  println(sum(1, 2, 3))

  println("a = sum _\na(1,2,3)")
  val a = sum _
  println(a(1, 2, 3))

  println("b = sum(1, _: Int, 3)\nb(8):")
  val b = sum(1, _: Int, 3)
  println(b(8))

  println("someNumbers.foreach(sumNumbers += _)::")
  var sumNumbers = 0
  someNumbers.foreach(sumNumbers += _)
  println(sumNumbers)


  println("\n\nclojures:")

  def makeIncreaser(more: Int) = (x: Int) => x + more

  val inc1 = makeIncreaser(1)
  val inc66 = makeIncreaser(66)
  println(inc1(1))
  println(inc66(600))

  println(makeIncreaser(10)(20))

  println("repeated parameter (last one):")

  def echo(a: String*) =
    for (b <- a) println(b)

  echo()
  echo("one")
  echo("one", "two", "three")

  println("Pass array as separate arguments: (arr: _*)")
  val arr = Array("what's", "up", "doc?")
  echo(arr: _*) // pass array

  println("named parameters & default values::")

  def speed(distance: Float = 10, time: Float = 1) = distance / time

  println(speed(100, 10))
  println(speed(distance = 100, time = 10))
  println(speed(time = 10, distance = 100))

  println(speed())
  println(speed(time = 10))

}
