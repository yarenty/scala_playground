package com.yarenty.monads

import rx.lang.scala._
/**
  * Created by yarenty on 03/12/2016.
  * (C)2015 SkyCorp Ltd.
  */
object Subscribe {


  def main(args: Array[String]): Unit = {
    val subscription = Subscription { println("Bye") }
    subscription.unsubscribe() // prints the message
    subscription.unsubscribe() // doesn't print it again
  }
}
