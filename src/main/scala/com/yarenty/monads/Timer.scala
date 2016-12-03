package com.yarenty.monads


import rx.lang.scala._
import scala.concurrent.duration._
import scala.concurrent._
import scala.util.{Try, Success, Failure}
import ExecutionContext.Implicits.global


/**
  * Created by yarenty on 03/12/2016.
  * (C)2015 SkyCorp Ltd.
  */
object Timer {


  val f: Future[String] = Future {
    Thread.sleep(10000)
    "future value"
  }

  val f2 = f map { s =>
    println("OK!")
    println("OK!")

  }


  def main(args: Array[String]): Unit = {

    println("start")

    val ticks: Observable[Long] = Observable.interval(1 seconds).take(6)
    val evens: Observable[Long] = ticks.filter(s => s % 2 == 0)

    ticks.subscribe(n => println("n = " + n))
    evens.subscribe(x => println("even = " + x))
    val bugs: Observable[Seq[Long]] = ticks.slidingBuffer(2, 1)
    val bugs2: Observable[Seq[Long]] = ticks.slidingBuffer(2, 2)

    val s = bugs.subscribe(b => println(b))
    val s2 = bugs2.subscribe(b => println(b))

    println("wait 10 sec:")
    Await.ready(f2, 60 seconds)
    println("exit")

    s.unsubscribe()
    s2.unsubscribe()
  }

}
