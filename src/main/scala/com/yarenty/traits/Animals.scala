package com.yarenty.traits

/**
 * Created by yarenty on 07/05/15.
 */
class Animals {
  def ping = println("animal")
}

trait Furry extends Animals {
  override def ping = {
    println("furry")
    super.ping
  }
}

trait HasLegs extends Animals {
  override def ping = {
    println("haslegs")
    super.ping
  }
}

trait FourLegged extends HasLegs {
  override def ping = {
    println("four legs")
    super.ping
  }
}

class Cat extends Animals with Furry with FourLegged {
  override def ping = {
    println("cat")
    super.ping
  }
}

/**
 * Any
 * \^
 * |
 * AnyRef
 * \^
 * |
 * Animal     <--- HasLegs
 * \^   \^           \^
 * |    |            |
 * |   Furry     FourLegged
 * |    \^        \^
 * |   /         /
 * |  /         /
 * Cat
 *
 *
 *
 *
 * Linearization created  in steps:
 * 1) Animal -> AnyRef -> Any
 *
 * 2) Furry -> Animal -> AnyRef -> Any
 *
 * //// here small twist - those who are next are added to the existing one!
 * [so there is HasLegs and then Furry
 * 3) FourLegged -> HasLegs -> Furry -> Animal -> AnyRef -> Any
 *
 *
 * FINAL:
 * Cat -> FourLegged -> HasLegs -> Furry -> Animal -> AnyRef -> Any
 *
 * super will go that way
 */


object Animals extends App {

  val c = new Cat

  c.ping
}