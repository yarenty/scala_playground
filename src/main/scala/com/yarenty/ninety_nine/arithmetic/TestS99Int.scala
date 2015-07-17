package com.yarenty.ninety_nine.arithmetic

/**
 * Created by yarenty on 10/07/2015.
 */
object TestS99Int {


  def main(args: Array[String]) {
    val s99 = new S99Int(3)

    println("S99 = " + s99 + " prime:: " + s99.isPrime)

    S99Int.gcd(21, 7)

    println("is 35 and 64 coprime?:" + new S99Int(35).isCoPrime(64))

  }
}
