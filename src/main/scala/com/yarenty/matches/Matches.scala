package com.yarenty.matches

/**
 * Created by yarenty on 01/05/15.
 */
object Matches extends App {

  def matchme(a: Array[String]) = {
    val arg = if (!a.isEmpty) a(0) else ""

    arg match {
      case "salt" => println("pepper")
      case "fish" => println("chips")
      case "eggs" => println("bacon")
      case _ => println("huh?")
    }
  }

  matchme(Array(null))
  matchme(Array("salt"))
  matchme(Array("fish"))
  matchme(Array("peppermint"))


}
