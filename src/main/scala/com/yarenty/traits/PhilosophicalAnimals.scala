package com.yarenty.traits

/**
 * Created by yarenty on 07/05/15.
 */

trait Philosophical {
  def philosophize = println("I consume memory so I am")
}

class Animal

trait HasLegs

class Frog extends Animal with Philosophical with HasLegs {
  override def toString = "green"

  override def philosophize = println("It ain't easy being " + toString + "!!")
}

object PhilosophicalAnimals extends App {

  val frog = new Frog

  println(frog)
  frog.philosophize


}
