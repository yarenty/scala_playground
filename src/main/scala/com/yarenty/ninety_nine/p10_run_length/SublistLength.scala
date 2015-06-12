package com.yarenty.ninety_nine.p10_run_length

/**
 * Created by yarenty on 12/06/15.
 */
object SublistLength {



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

  def encode[A](l:List[List[A]]): List[(A,Int)] = l match {
    case List() => Nil
    case x :: l => (x.head, x.length) :: encode (l)
  }


  def main(args: Array[String]) {

    val p = pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))

    println(p)

    val e = encode(p)

    println(e)
  }


}
