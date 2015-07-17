package com.yarenty.ninety_nine

import scala.language.implicitConversions

/**
 * Created by yarenty on 10/07/2015.
 */
package arithmetic {

class S99Int(val start: Int) {

  import S99Int._

  def isPrime: Boolean =
    (start > 1) && (primes takeWhile {
      _ <= Math.sqrt(start)
    } forall {
      start % _ != 0
    })

  implicit def isCoPrime(n: Int): Boolean = {
    gcd(start, n) == 1
  }
}

object S99Int {
  implicit def int2S99Int(i: Int): S99Int = new S99Int(i)

  val primes = Stream.cons(2, Stream(3, 2) filter {
    _.isPrime
  })

  def gcd(x: Int, y: Int): Int = {
    val o = x % y
    if (o == 0 || x == y) y
    else
      gcd(y, o)
  }


}

}
