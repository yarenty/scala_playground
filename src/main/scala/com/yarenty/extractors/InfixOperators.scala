package com.yarenty.extractors

/**
  * Created by yarenty on 22/08/2016.
  * (C)2015 SkyCorp Ltd.
  */
object InfixOperators {


  def main(args: Array[String]) {

    val xs = 58 #:: 43 #:: 93 #:: Stream.empty
    val o = xs match {
      case first #:: second #:: _ => first - second
      case _ => -1
    }

    println(o)

  }
}
