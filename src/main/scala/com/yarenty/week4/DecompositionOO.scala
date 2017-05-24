package com.yarenty.week4

/**
  * Simple Object Oriented Decomposition
  *
  *
  * issues: 
  *  - add new method - define in all childs
  *  - a * b + a * c  -> a * ( b + c )
  *
  * Created by yarenty on 24/05/2017.
  */
class DecompositionOO {

  trait Expr {
    def eval: Int
  }

  class Number(n: Int) extends Expr {
    override def eval: Int = n
  }

  class SumOO(e1: Expr, e2: Expr) extends Expr {
    override def eval: Int = e1.eval + e2.eval
  }

}