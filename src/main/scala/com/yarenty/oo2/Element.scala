package com.yarenty.oo2

/**
 * Created by yarenty on 05/05/15.
 */
abstract class Element {

  //abstract method
  def contents: Array[String]

  //parameterless method - no need for ()
  def height: Int = contents.length

  def width: Int = if (height == 0) 0 else contents(0).length

}
