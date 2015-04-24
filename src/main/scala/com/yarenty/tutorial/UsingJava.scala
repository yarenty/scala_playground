package com.yarenty.tutorial

import java.text.DateFormat
import java.text.DateFormat._
import java.util.{Date, Locale}

/**
 * Example of using java inside scala program.
 * Created by yarenty on 24/04/15.
 */
object UsingJava {

  def main(args: Array[String]) {

    val now = new Date
    val df = DateFormat.getDateInstance(LONG, Locale.FRANCE)

    println(df format now)
  }


}
