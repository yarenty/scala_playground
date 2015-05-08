package com.yarenty.oo2

//imports itself to have access to elem method

import com.yarenty.oo2.Element.elem

/**
 * Created by yarenty on 05/05/15.
 */
abstract class Element {

  //abstract method
  def contents: Array[String]

  //parameterless method - no need for ()
  def height: Int = contents.length

  def width: Int = if (height == 0) 0 else contents(0).length


  def above (that: Element): Element = {
    val t1 = this widen that.width
    val t2 = that widen this.width
    elem(t1.contents ++ t2.contents)
  }

  /*
   def besideImperative(that: Element): Element = {
     val contents = new Array[String](this.contents.length)
     for (i <- 0 until this.contents.length)
       contents(i) = this.contents(i) + that.contents(i)
     elem(contents)
   }
   */

  def beside(that: Element): Element = {
    val t1 = this heighten that.height
    val t2 = that heighten this.height
    elem(
      for ((line1, line2) <- t1.contents zip t2.contents)
        yield line1 + line2
    )
  }

  override def toString = contents mkString "\n"


  def widen(w: Int): Element =
    if (w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', (w - width - left.width), height)
      left beside this beside right
    }

  def heighten(h: Int): Element =
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bottom = elem(' ', width, (h - height - top.height))
      top above this above bottom
    }
}


object Element {

  def elem(contents: Array[String]): Element = new ArrayElement(contents)

  def elem(chr: Char, width: Int, height: Int): Element = new UniformElement(chr, width, height)

  def elem(line: String): Element = new LineElement(line)


  // here they are private classes - outside world don't need to know implementation
  private class ArrayElement (val contents: Array[String]) extends Element {

  }

  private class LineElement (s: String) extends Element {
    val contents = Array(s)

    override def width = s.length

    override def height = 1

  }

  private class UniformElement (
                                 ch: Char,
                                 override val width: Int,
                                 override val height: Int

                                 ) extends Element {

    if (width <= 0) throw new IllegalArgumentException
    private val line = ch.toString * width

    def contents = Array.fill(height)(line)


  }

}