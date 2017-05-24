package com.yarenty.week4

/**
  * Created by yarenty on 24/05/2017.
  */
object DecompositionPatterns extends App {

  trait Expr {
    def eval: Int =
      this match {
        case Number(n) => n
        case Sum(e1, e2) => e1.eval + e2.eval
        case Prod(e1, e2) => e1.eval * e2.eval
      }

    def show: String =
      this match {
        case Number(n) => n.toString
        case Sum(e1, e2) => e1.show + " + " + e2.show
        case Prod(e1:Sum, e2:Sum) => "(" + e1.show + ") * (" + e2.show + ")"
        case Prod(e1:Sum, e2) => "(" + e1.show + ") * " + e2.show + ""
        case Prod(e1, e2:Sum) => "" + e1.show + " * (" + e2.show + ")"
        case Prod(e1, e2) => e1.show + " * " + e2.show 
      }
  }

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr


  val e = Sum(Number(4), Number(1))
  
  println(e.show)
  println(e.eval)

  val e2 = Prod(e, Number(2))
  println(e2.show)
  println(e2.eval)

}
