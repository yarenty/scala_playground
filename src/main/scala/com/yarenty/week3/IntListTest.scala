package com.yarenty.week3

import java.util.NoSuchElementException

/**
  * Created by yarenty on 17/05/2017.
  */
object IntListTest extends App {


  //create singletons 
  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])


  val s1 = singleton(1)
  val st = singleton(true)


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