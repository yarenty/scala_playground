package com.yarenty.tutorial.classes

/**
 * Created by yarenty on 24/04/15.
 */
object ComplexNumbers {

  def main(args: Array[String]) {
    val c1 = new ComplexBasic(1.2, 3.4)
    // need to use brackets when calling
    println("Number is :: " + c1.re() + "+" + c1.im() + "i")

    val c2 = new Complex(1.2, 3.4)
    println("Number is :: " + c2)

    val c3 = new Complex(1.2, -3.4)
    println("Number is :: " + c2)

  }

}
