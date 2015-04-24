package com.yarenty.tutorial.classes

/**
 * Updated version of ComplexBasic, where method dont need to have parentheses if they don't use params.
 * Also introduction of override.
 * Created by yarenty on 24/04/15.
 */
class Complex(real: Double, imaginary: Double) {

  def re = real

  def im = imaginary

  override def toString() = "" + re + (if (im < 0) "" else "+") + im + "i"

}

