package com.yarenty.futures

/**
  * Created by yarenty on 29/07/2016.
  * (C)2015 SkyCorp Ltd.
  */

import scala.concurrent.future
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Random



class Advanced extends Simple {

  /*
  override def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("start grinding...")
    Thread.sleep(Random.nextInt(2000))
    if (beans == "baked beans") throw GrindingException("are you joking?")
    println("finished grinding...")
    s"ground coffee of $beans"
  }

  override def heatWater(water: Water): Future[Water] = Future {
    println("heating the water now")
    Thread.sleep(Random.nextInt(2000))
    println("hot, it's hot!")
    water.copy(temperature = 85)
  }

  override def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    println("milk frothing system engaged!")
    Thread.sleep(Random.nextInt(2000))
    println("shutting down milk frothing system")
    s"frothed $milk"
  }

  override def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("happy brewing :)")
    Thread.sleep(Random.nextInt(2000))
    println("it's brewed!")
    "espresso"
  }
  */
}




object Advanced {

  def main(args: Array[String]) {
    println((new Advanced).prepareCappuccino)
  }

}
