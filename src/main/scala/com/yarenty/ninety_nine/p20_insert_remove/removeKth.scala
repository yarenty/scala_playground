package com.yarenty.ninety_nine.p20_insert_remove

/**
 * Input:
 * List(1, 2, 3, 4, 5, 6, 7, 8, 9)
 *
 * remove(4, list)
 *
 * Output:
 * (List(1, 2, 3, 4, 6, 7, 8, 9),5)
 *
 *
 * Created by yarenty on 03/07/15.
 */
object removeKth {

  def remove[A](k: Int, list: List[A]): (List[A], A) = {
    if (k < 0 || k >= list.size) throw new NoSuchElementException
    (list.take(k) ::: list.drop(k + 1), list(k))
  }

  def removeMatch[A](k: Int, list: List[A]): (List[A], A) = list.splitAt(k) match {
    case (Nil, _) if k < 0 => throw new NoSuchElementException
    case (pre, e :: post) => (pre ::: post, e)
    case (pre, Nil) => throw new NoSuchElementException

  }

  def main(args: Array[String]) {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(remove(4, list))
    println(removeMatch(4, list))
    //println(remove(9, list))
    //println(removeMatch(9, list))
  }

}
