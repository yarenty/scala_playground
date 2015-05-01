package com.yarenty.breaks

import java.io.{BufferedReader, InputStreamReader}

import scala.util.control.Breaks

/**
 * There are no Java type breaks - you need to use control package and special class.
 * "break" is just special exception that is used inside "breakable".
 * Created by yarenty on 01/05/15.
 */
object ReadInputLine extends App {

  val in = new BufferedReader(new InputStreamReader(System.in))


  Breaks.breakable {
    while (true) {
      print("? :")
      if (in.readLine() == "") Breaks.break;
    }

  }

}
