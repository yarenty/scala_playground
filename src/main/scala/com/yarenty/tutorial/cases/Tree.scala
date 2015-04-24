package com.yarenty.tutorial.cases


/**
 * Simple calculator:
 * - nodes are operations
 * - leaves are values
 *
 * Created by yarenty on 24/04/15.
 */
abstract class Tree

/**
 * case classes means:
 * - new is not mandatory
 * - getters automatically defined
 * - equals and hashCode is there
 * - source form of toString
 * - pattern matching
 */
case class Sum(l: Tree, r: Tree) extends Tree

case class Var(n: String) extends Tree

case class Const(v: Int) extends Tree


/**
 * And here becomes body ;-)
 */
object Tree {

  type Environment = String => Int


  /**
   * The evaluation function works by performing pattern matching on the tree t.
   * @param t
   * @param env
   * @return
   */
  def eval(t: Tree, env: Environment): Int = t match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Var(n) => env(n)
    case Const(v) => v
  }


  def derive(t: Tree, v: String): Tree = t match {
    case Sum(l, r) => Sum(derive(l, v), derive(r, v))
    case Var(n) if (v == n) => Const(1) //case with guard
    case _ => Const(0)
  }

  def main(args: Array[String]) {
    val exp: Tree = Sum(Sum(Var("x"), Var("x")), Sum(Const(7), Var("y")))
    val env: Environment = {
      case "x" => 5
      case "y" => 7
    }
    println("Expression: " + exp)
    println("Evaluation with x=5,y=7: " + eval(exp, env))
    println("Derivative relations to x:\n " + derive(exp, "x"))
    println("Derivative relative to y:\n " + derive(exp, "y"))
  }
}





