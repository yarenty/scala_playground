package com.yarenty.week4

/**
  * Created by yarenty on 24/05/2017.
  */
object DecompositionPatterns extends App {
  
  trait Expr
  case class Number(n:Int) extends Expr
  case class Sum(e1:Expr,e2:Expr) extends Expr

  
  def eval(e:Expr):Int = {
    e match {
      case Number(n) => n
      case Sum(e1,e2) => eval(e1) + eval(e2)
    }
  }
  
}
