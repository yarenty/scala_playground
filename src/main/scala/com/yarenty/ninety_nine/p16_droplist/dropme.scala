package com.yarenty.ninety_nine.p16_droplist

/**
 * Created by yarenty on 17/06/15.
 */
object dropme {

  /**
   * Drop every Nth element from a list.
Example:
scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
   */

  def drop[A](n: Int, lx: List[A]): List[A] = {
    def dropR[A](c: Int, rl: List[A]): List[A] = (c, rl) match {
      case (_, Nil) => Nil
      case (1, _ :: tail) => dropR(n, tail) //here is real drop
      case (_, head :: tail) => head :: dropR(c - 1, tail) //just go deeper
    }

    dropR(n, lx)
  }


  // Functional.
  def dropFunctional[A](n: Int, ls: List[A]): List[A] =
    ls.zipWithIndex filter { v => (v._2 + 1) % n != 0 } map { _._1 }


  def main(args: Array[String]) {
    val list = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    println(drop(3, list))
    println(drop(2, list))

    println(dropFunctional(2,list))


    val l1 = list.zipWithIndex
    println(l1)

    val l2 = l1.filter( x=> (x._2+1) % 2!=0)

    println(l2)

    val l3 = l2 map( _._1)

    println(l3)

  }

}
