package com.yarenty.oo2

import com.yarenty.oo2.Element.elem
import org.scalatest.FlatSpec
import org.specs2.matcher.ShouldMatchers

/**
 * Created by yarenty on 08/05/15.
 */
class ElementSpec extends FlatSpec with ShouldMatchers {

  "A Uniform Element" should "have a width equal to the passed value" in {
    val e = elem('x', 3, 4)
    info(" width is 3")
    assert(e.width == 3)
    e.width should be_==(5) //not working ;-(
  }

  it should " height equal to passed value" in {
    val e = elem('x', 3, 4)
    info("height is 4 ")
    assert(e.height == 4)
  }

  it should " throw IAE if passed negative width" in {
    intercept[IllegalArgumentException] {
      info("for width 2 - no exception")
      val e = elem('x', 2, 4)
      info("for width -2 - exception")
      val e2 = elem('x', -2, 4)
    }

  }

}
