package com.yarenty.oo2

/**
 * Created by yarenty on 06/05/15.
 */
class LineElement (s: String) extends ArrayElement(Array(s)) {
  override def width = s.length

  override def height = 1

}
