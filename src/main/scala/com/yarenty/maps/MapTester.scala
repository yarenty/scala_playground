package com.yarenty.maps

/**
  * Created by yarenty on 02/12/2016.
  * (C)2015 SkyCorp Ltd.
  */
object MapTester {



  val myMap = Map("I"->1, "V" -> 5, "X"->10)

  def main(args: Array[String]): Unit = {
    println(myMap("I"))        // 1

    println(myMap get "A")      // None
    println(myMap get "I")        //Some(1)
    println(myMap updated ("V", 15))     //Map(I -> 1, V -> 15, X -> 10)

    println(myMap get "V")    //Some(5)
    println(myMap("V"))     //5


    val newMap =   myMap updated ("V", 15)

    println(newMap get "V")    //Some(15)
    println(newMap("V"))       //15

  }

}
