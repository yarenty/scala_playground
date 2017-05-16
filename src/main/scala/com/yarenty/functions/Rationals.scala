package com.yarenty.functions

/**
  * Created by yarenty on 16/05/2017.
  */

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be positive")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private val g = gcd(x, y)

  def numer = x / g

  def denom = y / g

  def +(that: Rational) =
    new Rational(numer * that.denom + that.numer * denom,
      denom * that.denom)


  def unary_- = new Rational(-numer, denom)

  def -(that: Rational) = this + -that

  def <(that: Rational) = numer * that.denom < that.numer * denom

  def max(that: Rational) = if (this < that) that else this

  override def toString: String = numer + "/" + denom

}


object Rationals extends App {


  val a = new Rational(1, 2)
  println(a.numer)
  println(a.denom)

  val b = new Rational(2, 3)

  println(a + b)


  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)

  println(x - y - (z))
  println(y + y)
  println(y max z)
  println(y < z)

  println(new Rational(2))


}
