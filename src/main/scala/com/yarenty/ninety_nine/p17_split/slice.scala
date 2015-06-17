package com.yarenty.ninety_nine.p17_split

/**
 * Created by yarenty on 17/06/15.
 */
object slice {


  def slice[A](n: Int, k: Int, lx: List[A]): List[A] = {
    def sliceR[A](c: Int, k: Int, rl: List[A]): List[A] = (c, k, rl) match {
      case (_, _, Nil) => Nil
      case (0, 0, _) => Nil //counter is zero - so split at (nil, whatever is left)
      case (0, x, head :: tail) => head :: sliceR(0, k - 1, tail)
      case (n, k, head :: tail) => sliceR(n - 1, k - 1, tail)
    } //just go deeper


    sliceR(n, k, lx)
  }


  // Functional.
  def sliceFunctional[A] (n: Int, k: Int, ls: List[A]): List[A] =
    ls.drop(n).take(k - n)


  def main (args: Array[String]) {
    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    println(slice(3, 7, list))

    println(sliceFunctional(3, 7, list))

    println(list.slice(3, 7))


  }
}
