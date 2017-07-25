package com.yarenty.parrallel_programming.threads

/**
  * Created by yarenty on 25/07/2017.
  */
class HelloThread extends Thread{

  override def run(): Unit = {
    
    println("Hello World")
  }
}





object TTest extends App {
  
  val t  = new HelloThread
  
  println("a")
  t.start()
  println("b")
  t.join()
  println("c")
  println("d")
  
  
}