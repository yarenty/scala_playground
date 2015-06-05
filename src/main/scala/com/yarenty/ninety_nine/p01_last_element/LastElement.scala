package com.yarenty.ninety_nine.p01_last_element

/**
 * Find last element of a list
 * Created by yarenty on 05/06/15.
 */
object LastElement {

  def last(lx: List[Int]): Int = lx match {
    case Nil => 0
    case l :: Nil => l
    case l :: lx => last(lx)
  }

  def lastReverse(lx: List[Int]): Int = lx.reverse.head


  def main(args: Array[String]) {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8)

    println(last(l))

    println(lastReverse(l))
  }


}
