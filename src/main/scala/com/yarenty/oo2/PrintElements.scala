package com.yarenty.oo2

/**
 * Created by yarenty on 06/05/15.
 */
object PrintElements {

  def main(args: Array[String]) {
    val ae = new ArrayElement(Array("hello", "world"))
    println(ae.contents)

    println("Height::" + ae.height)
    println("Width::" + ae.width)


    val e: Element = ae

    val l: ArrayElement = new LineElement("seriously?")

    val ue: Element = new UniformElement('#', 2, 4)

  }
}
