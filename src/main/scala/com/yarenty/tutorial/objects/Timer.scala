package com.yarenty.tutorial.objects

/**
 * Demonstration that functions are objects too.
 *
 * Created by yarenty on 24/04/15.
 */
object Timer {

  def oncePerSecond(callback: () => Unit): Unit = {
    while (true) {
      callback()
      Thread sleep 1000
    }
  }

  def timeFlies(): Unit = {
    println("time flies like arrow...")
  }

  def main(args: Array[String]) {
    oncePerSecond(timeFlies)
  }
}
