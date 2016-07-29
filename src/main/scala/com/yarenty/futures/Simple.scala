package com.yarenty.futures

import scala.util.Try

/**
  * Created by yarenty on 29/07/2016.
  * (C)2015 SkyCorp Ltd.
  */
case class Water(temperature: Int)


// some exceptions for things that might go wrong in the individual steps
// (we'll need some of them later, use the others when experimenting
// with the code):

case class GrindingException(msg: String) extends Exception(msg)

case class FrothingException(msg: String) extends Exception(msg)

case class WaterBoilingException(msg: String) extends Exception(msg)

case class BrewingException(msg: String) extends Exception(msg)


class Simple {

  // Some type aliases, just for getting more meaningful method signatures:
  type CoffeeBeans = String
  type GroundCoffee = String
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String


  // dummy implementations of the individual steps:
  def grind(beans: CoffeeBeans): GroundCoffee = s"ground coffee of $beans"

  def heatWater(water: Water): Water = water.copy(temperature = 85)

  def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"

  def brew(coffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"

  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"


  // going through these steps sequentially:
  def prepareCappuccino(): Try[Cappuccino] = for {
    ground <- Try(grind("arabica beans"))
    water <- Try(heatWater(Water(25)))
    espresso <- Try(brew(ground, water))
    foam <- Try(frothMilk("milk"))
  } yield combine(espresso, foam)

}

object Simple {

  def main(args: Array[String]) {
    println((new Simple).prepareCappuccino)
  }

}
