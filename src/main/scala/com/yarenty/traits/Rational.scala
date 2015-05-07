package com.yarenty.traits

/**
 * By using trait Ordered all method for < <= >= > are automatically created by providing body to the method compare().
 * Created by yarenty on 07/05/15.
 */
class Rational(n: Int, d: Int) extends Ordered[Rational] {

  val num = n
  val den = d

  def compare(that: Rational) =
    (this.num * that.den) - (that.num * this.den)

}


object Rational extends App {
  val r1 = new Rational(1, 2)
  val r2 = new Rational(1, 3)
  val r3 = new Rational(2, 3)


  println(r1 > r2)
  println(r2 > r3)
  println(r1 > r3)


}
