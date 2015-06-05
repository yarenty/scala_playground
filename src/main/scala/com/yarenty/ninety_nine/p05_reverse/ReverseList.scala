package com.yarenty.ninety_nine.p05_reverse

/**
 * Created by yarenty on 05/06/15.
 */
object ReverseList {


  def reverse(lx: List[Int]): List[Int] = lx match {
    case Nil => throw new NoSuchElementException
    case l :: Nil => List(l)
    case l :: lx => reverse(lx) ::: List(l)
  }


  def reverseTail(lx: List[Int]): List[Int] = {

    def rev(lout: List[Int], lin: List[Int]): List[Int] = lin match {
      case Nil => lout
      case l :: ls => rev(l :: lout, ls)
    }

    rev(Nil, lx)
  }


  def main(args: Array[String]) {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8)

    println(reverse(l))
    println(reverseTail(l))

    println(l.reverse)
  }

}
