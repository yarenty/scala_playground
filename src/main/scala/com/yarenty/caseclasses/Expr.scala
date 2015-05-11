package com.yarenty.caseclasses

/**
 * Created by yarenty on 08/05/15.
 */
abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr


object Expr {


  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e //double negation
    case BinOp("+", e, Number(0)) => e // plus 0
    case BinOp("*", e, Number(1)) => e //multiply by 1
    case _ => expr
  }


  def main(args: Array[String]) {

    val v = Var("x")

    val op = BinOp("+", Number(1), v)

    println(v.name)
    println(op)
    println(op.left)
    println(op.right)

  }
}

