package com.yarenty.parrallel_programming.threads

/**
  * Created by yarenty on 25/07/2017.
  */
class HelloThread extends Thread {
  override def run(): Unit = {
    println("Hello World")
  }
}

class HelloThread2 extends Thread {
  override def run(): Unit = {
    println("Hello")
    println("World!!")
  }
}


object TTest extends App {

  val t = new HelloThread

  println("a")
  t.start()
  println("b")
  t.join()
  println("c")
  println("d")


  val t2 = new HelloThread2
  val t3 = new HelloThread2
  t2.start()
  t3.start()
  t2.join()
  t3.join() //random ;-)


  private var uid = 0L

  def getUniqueId(): Long = {
    uid = uid + 1
    uid
  }


  def startThread() = {
    val t = new Thread {
      override def run(): Unit = {
        val uids = for (i <- 0 until 10) yield getUniqueId()
        println(uids)
      }
    }
    t.start()
    t
  }


  startThread()
  startThread()


  private val x = new AnyRef {}
  private var suid = 0L
  def getSyncUniqueId(): Long = x.synchronized {
    suid = suid + 1
    suid
  }


  def startSyncThread() = {
    val t = new Thread {
      override def run(): Unit = {
        val uids = for (i <- 0 until 10) yield getSyncUniqueId()
        println(uids)
      }
    }
    t.start()
    t
  }

  startSyncThread()
  startSyncThread()
  

}