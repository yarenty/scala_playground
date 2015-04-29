package com.yarenty.classes

/**
 * Created by yarenty on 29/04/15.
 */
object SimpleApp extends App {

  //this is one big MAIN ;-)
  //what with threads?
  for (season <- List("fall", "winter", "spring", "summer"))
    println(season + ":" + ChecksumAccumulator.calculate(season))

}
