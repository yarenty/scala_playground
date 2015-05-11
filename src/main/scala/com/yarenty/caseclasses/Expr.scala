package com.yarenty.caseclasses

import com.yarenty.oo2.Element
import com.yarenty.oo2.Element.elem

/**
 * Created by yarenty on 08/05/15.
 */
sealed abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr


class ExprFormatter {

  private val opGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%"),
      Set("/")
    )

  private val precedence = {
    val assocs =
      for {i <- 0 until opGroups.length
           op <- opGroups(i)} yield op -> i
    assocs.toMap
  }

  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1

  private def format(e: Expr, enclPrec: Int): Element = e match {
    case Var(name) => elem(name)
    case Number(num) =>
      def stripDot (s: String) =
        if (s endsWith ".0") s.substring(0, s.length - 2)
        else s
      elem(stripDot(num.toString))
    case UnOp(op, arg) =>
      elem(op) beside format(arg, unaryPrecedence)
    case BinOp("/", left, right) =>
      val top = format(left, fractionPrecedence)
      val bot = format(right, fractionPrecedence)
      val line = elem('-', top.width max bot.width, 1)
      val frac = top above line above bot
      if (enclPrec != fractionPrecedence) frac
      else elem(" ") beside frac beside elem(" ")
    case BinOp(op, left, right) =>
      val opPrec = precedence(op)
      val l = format(left, opPrec)
      val r = format(right, opPrec + 1)
      val oper = l beside elem(" " + op + " ") beside r
      if (enclPrec <= opPrec) oper
      else elem("(") beside oper beside elem(")")
  }

  def format(e: Expr): Element = format(e, 0)


}


object Expr {


  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e //double negation
    case BinOp("+", e, Number(0)) => e // plus 0
    case BinOp("*", e, Number(1)) => e //multiply by 1
    case UnOp("abs", e@UnOp("abs", _)) => e //abs two times
    case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2)) //pattern guard ;-)
    case UnOp(op, e) => UnOp(op, simplifyTop(e))
    case BinOp(op, l, r) => BinOp(op, simplifyTop(l), simplifyTop(r))
    case _ => expr
  }


  def describe(e: Expr): String = e match {
    case Number(_) => "a number"
    case Var(_) => "a variable"
    //baceuse of sealed - there is warning here as BinOp and UnOp is missing
    case _ => throw new RuntimeException //should not happen ;-)
  }

  // and quite nicer solution:
  def describe2(e: Expr): String = (e: @unchecked) match {
    case Number(_) => "a number"
    case Var(_) => "a variable"
  }


  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }


  val withDefault: Option[Int] => Int = {
    case Some(x) => x
    case None => 0
  }


  val second: PartialFunction[List[Int], Int] = {
    case x :: y :: _ => y
  }

  def main(args: Array[String]) {

    val v = Var("x")

    val op = BinOp("+", Number(1), v)

    println(v.name)
    println(op)
    println(op.left)
    println(op.right)

    val capitals = Map("France" -> "Paris", "Japan" -> "Tokio")
    println("Japan capital is: " + show(capitals get "Japan"))
    println("France capital is: " + show(capitals get "France"))
    println("USA capital is: " + show(capitals get "USA"))


    val exp = new BinOp("*", Number(5), Number(3))

    // pattern variable definitions
    val BinOp(opr, left, right) = exp

    println(opr)
    println(left)
    println(right)

    println(withDefault(Some(10)))
    println(withDefault(None))


    println("second.isDefinedAt(List(1,2,3))::" + second.isDefinedAt(List(1, 2, 3)))
    println("second.isDefinedAt(List())::" + second.isDefinedAt(List()))

    //and patterns in for loop:
    for ((country, city) <- capitals) {
      println("The capital of " + country + " is " + city)
    }

    //not everything is matched:
    val res = List(Some("apple"), None, Some("orange"))
    for (Some(fruit) <- res)
      println(fruit)


    val expr = BinOp("/", Number(1),
      BinOp("*", Var("y"),
        BinOp("+", Number(1), Var("x"))))

    val f = new ExprFormatter
    println(f.format(expr))
  }
}

