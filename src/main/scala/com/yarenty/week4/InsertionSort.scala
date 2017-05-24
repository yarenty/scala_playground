package com.yarenty.week4

/**
  * Created by yarenty on 24/05/2017.
  */
object InsertionSort extends App{


  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if ( x <= y ) x :: xs  else  y :: insert(x, ys)
  }
  
  
  def isort(xs:List[Int]):List[Int] = xs match {
    case Nil => Nil
    case y::ys => insert(y, isort(ys))
  }
  
  val l = 21::2::15::7::9::Nil
  
  val s = isort(l)
  
  
  println( s.mkString(","))
}
