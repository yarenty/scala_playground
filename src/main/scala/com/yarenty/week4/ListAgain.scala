package com.yarenty.week4

import java.util.NoSuchElementException

/**
  * Created by yarenty on 23/05/2017.
  */

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

/**
  * List(1,2)
  * List(1)
  * List()
  */
object List {
  
  //List(1,2) = List.apply(1,2)
  
  def apply[T](x1:T,x2:T):List[T] = new Cons[T](x1,new Cons[T](x2, new Nil))
  def apply[T](x:T):List[T] = new Cons[T](x, new Nil)
  def apply[T]():List[T] =  new Nil
  
}