package com.yarenty.oo2

import com.yarenty.oo2.Element.elem
import org.scalatest.WordSpec
import org.scalatest.prop.Checkers

// this must contains import : import org.scalacheck.Prop._
import org.scalacheck.Prop._
// other wise it cannot find  '==>'

/**
 * Example OUTPUT:
 * Gave up after 9 successful property evaluations. 92 evaluations were discarded.
ScalaTestFailureLocation: com.yarenty.oo2.EkementCheckers$$anonfun$1$$anonfun$apply$mcV$sp$1 at (EkementCheckers.scala:15)
org.scalatest.exceptions.GeneratorDrivenPropertyCheckFailedException: Gave up after 9 successful property evaluations. 92 evaluations were discarded.

 * Created by yarenty on 08/05/15.
 */
class ElementCheckers extends WordSpec with Checkers {
  "elem result" must {
    "have passed width" in {
      check((w: Int) => ((w > 0 && w < 100) ==> (elem('x', w, 3).width == w)))
    }

    "have passed height" in {
      check((h: Int) => ((h > 0 && h < 100) ==> (elem('x', 1, h).height == h)))
    }
  }
}
