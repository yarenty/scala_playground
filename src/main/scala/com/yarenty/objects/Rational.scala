package com.yarenty.objects

import scala.language.implicitConversions

/**
 * Created by yarenty on 30/04/15.
 */
class Rational(n: Int, d: Int) {


  //checking preconditions
  require(d != 0)

  private val g = gcd(n.abs, d.abs) // find greatest common division (1 in normal case)

  val num = n / g
  val den = d / g

  //auxiliary constructor
  def this(n: Int) = this(n, 1)

  println("created: " + num + "/" + den)

  //greatest common division
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)


  private def gcdLoop(x: Long, y: Long): Long = {
    var a = x
    var b = y
    while (a != 0) {
      val temp = a
      a = b % a
      b = temp //if a == 0  b has last value
    }
  }


  override def toString() = num + "/" + den

  def + (that: Rational) = new Rational(num * that.den + that.num * den, den * that.den)

  def - (that: Rational) = new Rational(num * that.den - that.num * den, den * that.den)

  def < (that: Rational) = num * that.den < that.num * den

  def > (that: Rational) = num * that.den > that.num * den

  def <= (that: Rational) = !(>(that))

  def >= (that: Rational) = !(<(that))

  def * (that: Rational) = new Rational(num * that.num, den * that.den)

  def / (that: Rational) = new Rational(num * that.den, den * that.num)

  def max(that: Rational) = if (this < that) that else this

}


object Rational {

  implicit def intToRational(x: Int) = new Rational(x)

}

object RationalTest {

  def main(args: Array[String]) {
    val r1 = new Rational(1, 2)
    val r2 = new Rational(2, 3)

    println("r1=" + r1)
    println("r2=" + r2)
    println("sum=" + (r1 + r2))

    println("product=" + (r1 * r2))

    println("divide=" + (r1 / r2))

    println("greater=" + (r1 > r2))

    println("<=" + (r1 <= r2))

    println("max=" + (r1 max r2))

    println("r2*2 = " + (r2 * 2))

    println(" 3 * r2 = " + (3 * r2))
    println("r2 + 2 = " + (r2 + 2))
  }
}