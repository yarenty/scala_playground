package com.yarenty.ninety_nine.p09_sublists

/**
 * P09 (**) Pack consecutive duplicates of list elements into sublists.
If a list contains repeated elements they should be placed in separate sublists.
Example:

scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))

 * Created by yarenty on 10/06/15.
 */
object Sublists {


  def pack[A](lx: List[A]): List[List[A]] = {
    if (lx.isEmpty) List(List())
    else {
      val (packed, next) = lx span {
        _ == lx.head
      }
      if (next == Nil) List(packed)
      else packed :: pack(next)
    }

  }


  def main(args: Array[String]) {

    val p = pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))

    println(p)
  }
}
