package com.yarenty.week5


/**
  * Created by yarenty on 27/05/2017.
  */
object ListPlayground extends App {

  def show[T](l: List[T]) = {
    println(l.mkString(","))
  }

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case Nil => ys
    case z :: zs => z :: concat(zs, ys)
  }

  def reverse[T](xs: List[T]): List[T] = xs match {
    case Nil => xs
    case y :: ys => reverse(ys) ++ List(y)
  }

  def removeAt[T](xs: List[T], n: Int): List[T] = xs match {
    case Nil => xs
    case y :: ys => if (n == 0) removeAt(ys, n - 1)
    else y :: removeAt(ys, n - 1)
  }


  def removeAt2[T](xs: List[T], n: Int): List[T] = (xs take n) ::: (xs drop n+1)
  


  val l = List(1, 2, 3, 4, 5)
  show(l)
  val l2 = List(6, 7, 8, 9, 10)
  show(l2)

  show(l.updated(3, 66))
  show(concat(l, l2))
  show(reverse(l))
  show(removeAt(l,3))
  show(removeAt(l,8))
  show(removeAt2(l,3))
  show(removeAt2(l,8))

}
