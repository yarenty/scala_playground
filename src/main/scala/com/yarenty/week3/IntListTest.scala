package com.yarenty.week3

import java.util.NoSuchElementException

/**
  * Created by yarenty on 17/05/2017.
  */
object IntListTest extends App {


  //create singletons 
  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])


  def nth[T](n: Int, l: List[T]): T = {
    if (l.isEmpty) throw new IndexOutOfBoundsException("no more ")
    else if (n == 0) l.head
    else nth(n - 1, l.tail)

  }

  val s1 = singleton(1)
  val st = singleton(true)
  val l = new Cons(1, new Cons(2, new Cons(3, singleton(4))))

  println(nth(2, l))
  println(nth(3, l))
  println(nth(5, l))

}


trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false
}

class Nil[T] extends List[T] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException("Nil.head")

  override def tail: Nothing = throw new NoSuchElementException("Nil.tail")

}