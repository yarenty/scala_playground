package com.yarenty.ninety_nine.p04_length

/**
 * Created by yarenty on 05/06/15.
 */
object NoElements {


  def length(lx: List[Int]): Int = lx match {
    case Nil => 0
    case l :: lx => 1 + length(lx)
  }

  /**
   * Use tail-call recursion - pre-compiled by scala to work in constant space.
   * @param lx
   * @return
   */
  def lengthTail(lx: List[Int]): Int = {

    def lR(len: Int, ls: List[Int]): Int = ls match {
      case Nil => len
      case l :: ls => lR(len + 1, ls)
    }

    lR(0, lx)
  }


  def main(args: Array[String]) {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8)

    println(length(l))
    println(lengthTail(l))
    println(l.length)

  }
}
