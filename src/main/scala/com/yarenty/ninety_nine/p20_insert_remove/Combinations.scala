package com.yarenty.ninety_nine.p20_insert_remove

/**
 * Created by yarenty on 07/07/15.
 */
object Combinations {

  def flatMapSub[A, B] (ls: List[A])(f: (List[A]) => List[B]): List[B] = ls match {
    case Nil => Nil
    case sublist@(_ :: tail) => f(sublist) ::: flatMapSub(tail)(f)
  }

  // like list.flatMap, but instead of passing each element to the function
  // is passes successive sublist of L
  def combinations[A](n: Int, ls: List[A]): List[List[A]] =
    if (n == 0) List(Nil)
    else flatMapSub(ls) { sl => combinations(n - 1, sl.tail) map {
      sl.head :: _
    }
    }


  def main(args: Array[String]) {
    println(combinations(3, List('a, 'b, 'c, 'd)))
    println(combinations(3, List('a, 'b, 'c, 'd, 'e)))
  }
}
