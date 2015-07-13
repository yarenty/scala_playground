package com.yarenty.ninety_nine.arithmetic

/**
 * Created by yarenty on 13/07/2015.
 */
object euclid {


  def gcd(x: Int, y: Int): Int = {
    val o = x % y
    if (o == 0) y
    else
    if (x == y) y
    else
      gcd(y, o)
  }

  def main(args: Array[String]) {

    println(gcd(1071, 462))
  }


}
