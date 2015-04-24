package com.yarenty.tutorial.genericity

/**
 * And .. no need to cast value
 * Created by yarenty on 24/04/15.
 */
object IntegerReference {

  def main(args: Array[String]) {
    val cell = new Reference[Int]
    cell.set(66)
    println("Reference contains the third of " + (cell.get * 3))
  }

}
