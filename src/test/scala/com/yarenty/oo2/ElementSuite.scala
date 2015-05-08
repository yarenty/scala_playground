package com.yarenty.oo2

import com.yarenty.oo2.Element.elem
import org.scalatest.{FunSuite, GivenWhenThen}

/**
 * Created by yarenty on 08/05/15.
 */
class ElementSuite extends FunSuite with GivenWhenThen {

  test("uniform element test") {
    Given("simple uniform element")
    val e = elem('x', 2, 3)
    When("element is created as 2,3 ")

    Then("width should be 2")
    assert(e.width === 2)

    assertResult(2) {
      e.width
    }

    info("that's it")
  }

  test("element with wrong size") {
    intercept[IllegalArgumentException] {
      elem('x', -2, 3)
    }
    info("size less on equal 0 should throw exception")
  }

  def testUniformElement() = {
    val e = elem('x', 2, 3)
    assert(e.width == 2)
    println("all good")
  }

}
