package com.yarenty.ninety_nine.p08_duplicates

/**
 * Eliminate consecutive duplicates of list elements.
If a list contains repeated elements they should be replaced with a single copy of the element.
The order of the elements should not be changed.
Example:

scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)

 * Created by yarenty on 10/06/15.
 */
object EliminateDuplicates {


  def compress[A](lx: List[A]): List[A] = {

    def internal[A](ls: List[A], out: List[A]): List[A] = ls match {
      case List() => out
      case l :: ls => if (l == out.last) internal(ls, out) else internal(ls, out ::: List(l))
    }

    internal(lx, List(lx.head))

  }

  def main(args: Array[String]) {


    val c = compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))

    println(c)
  }
}
