package com.yarenty.ninety_nine.p03_nth_element

import java.util.NoSuchElementException

/**
 * Find the n-th element of a list.
 * Created by yarenty on 05/06/15.
 */
object NthElement {


  def nth(n: Int, lx: List[Int]): Int = {
    if (lx == Nil) throw new NoSuchElementException
    else
    if (n == 0) lx.head
    else nth(n - 1, lx.tail)
  }


  def main(args: Array[String]) {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8)

    println(nth(0, l))

    println(nth(3, l))

    println(nth(10, l))

  }
}
