package com.yarenty.traits

import org.scalatest.junit.JUnit3Suite

/**
 * Created by yarenty on 08/05/15.
 */
class QueueingSuite extends JUnit3Suite {

  override def setUp(): Unit = {
    //do nothing
  }


  def testQueuing() {

    //Order is extremelly significant - starting from right
    val qa = (new BasicIntQueue with Incrementing with Filtering)

    qa.put(-1)
    qa.put(0)
    qa.put(1)
    assert(qa.get == 1)
    assert(qa.get == 2)

    println("tested basic int queue with incrementing and filtering ")
  }

  def testJunit(): Unit = {
    println("simple check if junit works")
    assert(2 == 2)
  }

}
