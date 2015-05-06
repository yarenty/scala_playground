package com.yarenty.oo2

import com.yarenty.oo2.Element.elem

/**
 * Created by yarenty on 06/05/15.
 */
object PrintElements {

  def main(args: Array[String]) {
    val ae = Element.elem(Array("hello", "world"))
    println(ae.contents)

    println("Height::" + ae.height)
    println("Width::" + ae.width)


    val e: Element = ae

    val l: Element = elem("seriously?")

    val ue: Element = elem('#', 2, 4)


    println("zip on 2 arrays is creating touple :")
    (for ((line1, line2) <- Array(1, 2, 3) zip Array("a", "b"))
      yield line1 + line2).foreach(println)

    println(l)
    println(ae)
  }
}
