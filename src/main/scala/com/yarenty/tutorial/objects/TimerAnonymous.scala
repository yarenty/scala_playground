package com.yarenty.tutorial.objects

/**
 * Demonstration that functions are objects too.
 * This time using anonymous function for times flies.
 *
 * Created by yarenty on 24/04/15.
 */
object TimerAnonymous {

  def oncePerSecond(callback: () => Unit): Unit = {
    while (true) {
      callback()
      Thread sleep 1000
    }
  }

  def main(args: Array[String]) {

    // yep, this is anonymous  '(args,) => {..body..}'
    oncePerSecond(() => println("times flies like an arrow.."))

  }
}
