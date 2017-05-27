package com.yarenty.week5


/**
  * Created by yarenty on 27/05/2017.
  */
object ListPlayground  extends App{

  def show[T](l:List[T]) = {
    println(l.mkString(","))
  }
  
  def concat[T](xs:List[T],ys:List[T]):List[T] = xs match {
    case Nil => ys
    case z::zs => z :: concat(zs,ys)
  }
  
  def reverse[T](xs:List[T]):List[T] = xs match {
    case Nil => xs
    case y::ys => reverse(ys) ++ List(y) 
  }
  
  
  
  val l = List(1,2,3,4,5)
  show(l)
  val l2 = List(6,7,8,9,10)
  show(l2)
  
  show(l.updated(3,66))
  show(concat(l,l2))
  show(reverse(l))
  
}
