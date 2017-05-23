package com.yarenty.week4

import java.util.NoSuchElementException

import com.yarenty.week3.{IntSet, NonEmpty, Empty}

/**
  * Created by yarenty on 23/05/2017.
  */

trait List[+T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]

//  def prepend(e: T): List[T] = new Cons[T](e, this) -- not working can add Empy on begining
  def prepend[U >: T](e: U): List[U] = new Cons[U](e, this)
  
  
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false
}

//class Nil[T] extends List[T] {
//  override def isEmpty: Boolean = true
//
//  override def head: Nothing = throw new NoSuchElementException("Nil.head")
//
//  override def tail: Nothing = throw new NoSuchElementException("Nil.tail")
//
//}


object Nil extends List[Nothing] {
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

  def apply[T](x1: T, x2: T): List[T] = new Cons[T](x1, new Cons[T](x2, Nil))

  def apply[T](x: T): List[T] = new Cons[T](x, Nil)

  def apply[T](): List[T] = Nil

  def f(xs: List[NonEmpty], x: List[IntSet]): List[Object] = xs prepend x
}