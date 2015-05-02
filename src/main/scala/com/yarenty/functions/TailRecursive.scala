package com.yarenty.functions

/**
 * Created by yarenty on 02/05/15.
 */
object TailRecursive extends App {

  def boom(x: Int): Int =
    if (x == 0) throw new Exception("boom!")
    else boom(x - 1) + 1


  try {
    boom(3)
  } catch {
    case ex: Exception => ex.printStackTrace
  }


  println("and now tail-call optimalizations (working as quick as while loops):")

  def bang(x: Int): Int =
    if (x == 0) throw new Exception("bang!")
    else bang(x - 1)


  try {
    bang(3)
  } catch {
    case ex: Exception => ex.printStackTrace
  }



}
