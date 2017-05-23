package com.yarenty.week4

/**
  * Created by yarenty on 23/05/2017.
  */
abstract class IdealBoolean {

  //if (cond) t else e  == cond.ifThenElse(t,e)
  def ifThenElse[T](t: => T, e: => T): T

  def &&(x: Boolean): Boolean = ifThenElse(x, false)

  def ||(x: Boolean): Boolean = ifThenElse(true, x)

  def unary_!(x: Boolean): Boolean = ifThenElse(false, true)

  def ==(x: Boolean): Boolean = ifThenElse(x, x.unary_!)

  def !=(x: Boolean): Boolean = ifThenElse(x.unary_!, x)


  //false < true
  def <(x: Boolean): Boolean = ifThenElse(false, x)

}


object TRUE extends IdealBoolean {
  override def ifThenElse[T](t: => T, e: => T): T = t
}


object FALSE extends IdealBoolean {
  override def ifThenElse[T](t: => T, e: => T): T = e
}