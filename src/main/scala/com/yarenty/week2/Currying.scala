package com.yarenty.week2

/**
 * Created by yarenty on 05/05/15.
 */
object Currying extends App {

  def plainOldSum(x: Int, y: Int) = x + y

  def curriedSum(x: Int)(y: Int) = x + y


  println(plainOldSum(1, 2))

  println(curriedSum(1)(2))

  val onePlus = curriedSum(1)_

  println(onePlus(2))


}
