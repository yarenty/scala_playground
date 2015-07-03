package com.yarenty.ninety_nine.p20_insert_remove

/**
 * p21
 *
 * Input:
 * List('a, 'b, 'c, 'd, 'e, 'f)
 *
 * insert('new, 3, list)
 *
 * Output:
 * List('a, 'b, 'c, 'new, 'd, 'e, 'f)
 *
 * Created by yarenty on 03/07/15.
 */
object insertKth {


  def insert[A](e: A, k: Int, list: List[A]): List[A] = {
    list.take(k) ::: List(e) ::: list.drop(k)
  }

  def insertA[A](e: A, k: Int, list: List[A]): List[A] = list.splitAt(k) match {
    case (pre, post) => pre ::: List(e) ::: post
  }


  def main(args: Array[String]) {
    val list = List('a, 'b, 'c, 'd, 'e, 'f)
    println(insert('new, 3, list))
    println(insertA('new, 3, list))
    println(insert('new, -1, list))
    println(insertA('new, -1, list))
    println(insert('new, 10, list))
    println(insertA('new, 10, list))

  }

}
