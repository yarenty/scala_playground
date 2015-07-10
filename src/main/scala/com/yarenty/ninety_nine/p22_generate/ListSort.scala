package com.yarenty.ninety_nine.p22_generate

/**
 * Created by yarenty on 09/07/15.
 */
object ListSort {
  //sort list of lists based on those lists size

  def sortList[A](l: List[List[A]]): List[List[A]] = {

    l.sortBy(lx => lx.size)
  }

  def encode[A](l: List[List[A]]): List[(A, Int)] = l match {
    case List() => Nil
    case x :: l => (x.head, x.length) :: encode(l)
  }


  def sortFreq[A](l: List[List[A]]): List[List[A]] = {

    val lmap = l map { s => (s, s.size) }

    println("lmap::" + lmap)


    val lsorted = lmap sortWith {
      _._2 < _._2
    }

    println("lsorted::" + lsorted)

    val swapped = Map(lsorted map {
      _.swap
    }: _*)

    println("swapped::" + swapped)


    val f = Map(lsorted map { _.swap }: _*)

    l sortWith { (e1, e2) => f(e1.length).length < f(e2.length).length }


  }


  def main(args: Array[String]) {

    val list = List(List(1, 2), List(1, 2, 3), List(1), List(1, 2), List(1, 2, 3, 4, 5, 6))

    println(sortList(list))


    println("by freq: " + sortFreq(list))

  }


}
