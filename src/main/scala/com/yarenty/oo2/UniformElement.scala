package com.yarenty.oo2

/**
 * Created by yarenty on 06/05/15.
 */
class UniformElement (
                       ch: Char,
                       override val width: Int,
                       override val height: Int

                       ) extends Element {
  
  private val line = ch.toString * width

  def contents = Array.fill(height)(line)


}
