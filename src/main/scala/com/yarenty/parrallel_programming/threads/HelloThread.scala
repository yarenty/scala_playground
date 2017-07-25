package com.yarenty.parrallel_programming.threads

/**
  * Created by yarenty on 25/07/2017.
  */
class HelloThread extends Thread{

  override def run(): Unit = {
    
    println("Hello World")
  }
}


class HelloThread2 extends Thread{

  override def run(): Unit = {

    println("Hello")
    println("World!!")
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


  val t2  = new HelloThread2
  val t3  = new HelloThread2
  t2.start()
  t3.start()
  t2.join()
  t3.join()  //random ;-)
}