package com.yarenty.ninety_nine.p02_last_but_one

/**
 * Find last but one element
 * Created by yarenty on 05/06/15.
 */
object LastButOne {

  def last(lx: List[Int]): Int = lx match {
    case Nil => throw new NoSuchElementException
    case l :: Nil => throw new NoSuchElementException
    case l :: l2 :: Nil => l
    case l :: l2 :: lx => last(l2 :: lx)
  }

  def lastReverse(lx: List[Int]): Int = lx.reverse.tail.head


  def main(args: Array[String]) {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8)

    println(last(l))

    println(lastReverse(l))
  }


}