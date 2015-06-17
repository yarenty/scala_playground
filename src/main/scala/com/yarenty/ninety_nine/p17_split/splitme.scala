package com.yarenty.ninety_nine.p17_split

/**
 * Created by yarenty on 17/06/15.
 */
object splitme {


  def split[A](n: Int, lx: List[A]): (List[A], List[A]) = {
    def splitR[A](c: Int, rl: List[A]): (List[A], List[A]) = (c, rl) match {
      case (_, Nil) => (Nil, Nil)
      case (0, list) => (Nil, list) //counter is zero - so split at (nil, whatever is left)
      case (n, head :: tail) => {
        val (pre, post) = splitR(n - 1, tail)
        (head :: pre, post)
      } //just go deeper
    }

    splitR(n, lx)
  }


  // Functional.
  def splitFunctional[A](n: Int, ls: List[A]): (List[A], List[A]) =
    ( ls.take(n),ls.drop(n))


  def main(args: Array[String]) {
    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    println(split(3, list))

    println(splitFunctional(3, list))

    println(list.splitAt(3))


  }
}
