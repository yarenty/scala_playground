package com.yarenty.traits

import scala.collection.mutable.ArrayBuffer

/**
 * Created by yarenty on 07/05/15.
 */

abstract class IntQueue {
  def get(): Int

  def put(x: Int)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  def get () = buf.remove(0)

  def put(x: Int) {
    buf += x
  }

}

/**
 * Trait with stackable modifications - call to super will be done dynamically after another class or trait
 * provide concrete definition to the method.
 */
trait Doubling extends IntQueue {
  abstract override def put(x: Int) {
    super.put(2 * x)
  }
}


class MyDoubleQueue extends BasicIntQueue with Doubling


/**
 * And some more to see stackable stuff.
 */
trait Incrementing extends IntQueue {
  abstract override def put(x: Int) {
    super.put(1 + x)
  }
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) {
    if (x >= 0) super.put(x)
  }
}


object Queueing extends App {

  val q = new BasicIntQueue

  q.put(10)
  q.put(20)
  println(q.get)
  println(q.get)


  val qd = new MyDoubleQueue

  qd.put(1)
  qd.put(2)
  qd.put(3)
  println(qd.get)
  println(qd.get)
  println(qd.get)


  //Order is extremelly significant - starting from right
  val qa = (new BasicIntQueue with Incrementing with Filtering)

  qa.put(-1)
  qa.put(0)
  qa.put(1)
  println(qa.get)
  println(qa.get)


  val qb = (new BasicIntQueue with Filtering with Incrementing)

  qb.put(-1)
  qb.put(0)
  qb.put(1)
  println(qb.get)
  println(qb.get)
  println(qb.get)
}
