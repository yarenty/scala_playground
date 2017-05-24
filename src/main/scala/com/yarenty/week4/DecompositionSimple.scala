package com.yarenty.week4

/**
  * Created by yarenty on 23/05/2017.
  */
object DecompositionSimple extends App{

  trait Expr {

    //classifications
    def isNumber: Boolean

    def isSum: Boolean


    //accessors
    def numValue: Int

    def leftOp: Expr

    def rigthOp: Expr

  }


  class Number(n: Int) extends Expr {
    override def isNumber: Boolean = true

    override def isSum: Boolean = false

    override def numValue: Int = n

    override def leftOp: Expr = throw new Error("number left op")

    override def rigthOp: Expr = throw new Error("number right op")
  }


  class Sum(e1: Expr, e2: Expr) extends Expr {

    override def isNumber: Boolean = false

    override def isSum: Boolean = true

    override def numValue: Int = throw new Error("sum value")

    override def leftOp: Expr = e1

    override def rigthOp: Expr = e2
  }



    def eval(e: Expr): Int = {
      if (e.isNumber) e.numValue
      else if (e.isSum) eval(e.leftOp) + eval(e.rigthOp)
      else throw new Error("Unknown op")
    }


}