package com.yarenty.ninety_nine.p17_split

/**
 * Created by yarenty on 17/06/15.
 */
object rotate {


  // Functional.
  def rotateLeft[A] (n: Int, ls: List[A]): List[A] =
    if (n > 0)
      ls.drop(n) ::: ls.take(n)
    else ls.drop(ls.length + n) ::: ls.take(ls.length + n)


  def main (args: Array[String]) {
    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    println(rotateLeft(3, list))

    println(rotateLeft(-3, list))

  }

}
