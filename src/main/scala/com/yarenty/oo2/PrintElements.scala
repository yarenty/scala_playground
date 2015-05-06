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

    println(ue beside (ae above l above ue) beside ue)


    println(spiral)
  }


  def spiral: Element = {
    val space = elem(" ")
    val corner = elem("+")

    def show(nEdges: Int, direction: Int): Element = {
      if (nEdges == 1)
        corner
      else {
        val sp = show(nEdges - 1, (direction + 3) % 4)
        def verticalBar = elem('|', 1, sp.height)
        def horizontalBar = elem('-', sp.width, 1)

        direction match {
          case 0 => (corner beside horizontalBar) above (sp beside space)
          case 1 => (sp above space) beside (corner above verticalBar)
          case 2 => (space beside sp) above (horizontalBar beside corner)
          case _ => (verticalBar above corner) beside (space above sp)
        }
      }

    }

    show(20, 0)
  }
}
