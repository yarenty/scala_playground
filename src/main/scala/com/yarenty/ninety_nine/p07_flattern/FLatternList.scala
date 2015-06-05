package com.yarenty.ninety_nine.p07_flattern

/**
 * Created by yarenty on 05/06/15.
 */
object FLatternList {

  def flat(lx: List[Any]): List[Any] = lx flatMap {
    case ls: List[_] => flat(ls)
    case e => List(e)
  }


  def main(args: Array[String]) {
    val l = List(List(1, 2, 3), 4, 5, List(6, List(7, 8), 9), 10)

    println(flat(l))
    //println(l.fl)

  }

}
