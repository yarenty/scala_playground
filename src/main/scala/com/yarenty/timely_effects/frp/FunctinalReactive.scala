package com.yarenty.timely_effects.frp

/**
  * Created by yarenty on 21/07/2017.
  */


import scala.util.DynamicVariable

class Signal[T](expr: => T) {
  import Signal._
  private var myExpr: () => T = _
  private var myValue: T = _
  private var observers: Set[Signal[_]] = Set()
  private var observed: List[Signal[_]] = Nil
  update(expr)

  protected def computeValue(): Unit = {
    for (sig <- observed)
      sig.observers -= this
    observed = Nil
    val newValue = caller.withValue(this)(myExpr())
    /* Disable the following "optimization" for the assignment, because we
     * want to be able to track the actual dependency graph in the tests.
     */
    //if (myValue != newValue) {
    myValue = newValue
    val obs = observers
    observers = Set()
    obs.foreach(_.computeValue())
    //}
  }

  protected def update(expr: => T): Unit = {
    myExpr = () => expr
    computeValue()
  }

  def apply() = {
    observers += caller.value
    assert(!caller.value.observers.contains(this), "cyclic signal definition") // s() = s() + 1
    caller.value.observed ::= this
    myValue
  }
}

class Var[T](expr: => T) extends Signal[T](expr) {
  override def update(expr: => T): Unit = super.update(expr)
}

object Var {
  def apply[T](expr: => T) = new Var(expr)
}

object NoSignal extends Signal[Nothing](???) {
  override def computeValue() = ()
}

object Signal {
  val caller = new DynamicVariable[Signal[_]](NoSignal)
  def apply[T](expr: => T) = new Signal(expr)
}




class BankAccount {
   val balance = Var(0)

  def currentBalance = balance

  def deposit(amount: Int): Unit =
    if (amount > 0) {
      val b = balance()
      balance() = b + amount
    }

  def withdraw(amount: Int): Unit =
    if (0 < amount && amount <= balance()) {
      val b = balance()
      balance() = b - amount
    } else throw new Error("insufficient funds")

}



object FunctionalReactive extends App {
  
  def consolidated(accs:List[BankAccount]):Signal[Int] =
    Signal(accs.map(_.balance()).sum)
  
  println("Hello")
  val a = new BankAccount
  val b = new BankAccount
  val c = consolidated(List(a,b))

  
  println(c())

  a deposit 20

  println(c())

  b deposit 30

  println(c())

  val xchange = Signal(246.00)
  val inDollar = Signal(c()* xchange())
  println(inDollar())
  b withdraw 10
  println(inDollar())
  

}