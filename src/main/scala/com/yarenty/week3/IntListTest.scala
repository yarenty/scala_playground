package com.yarenty.week3

/**
  * Created by yarenty on 17/05/2017.
  */
object IntListTest extends App{

}


trait List[T]

class Cons[T](val head:T, val tail: List[T]) extends List[T]

class Nil[T] extends List[T]