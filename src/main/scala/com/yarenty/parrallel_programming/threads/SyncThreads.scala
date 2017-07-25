package com.yarenty.parrallel_programming.threads

/**
  * Created by yarenty on 25/07/2017.
  */

class Account(private var amount: Int = 0) {
  def transfer(target: Account, n: Int) =
    this.synchronized {
      target.synchronized {
        this.amount -= n
        target.amount += n
      }
    }
}


object SyncThreads extends App {
  
  
  def startThread(a:Account, b:Account, n:Int) = {
    val t = new Thread {
      override def run(): Unit = {
        for (i<-0 until n) {
          a.transfer(b,1)
        }
      }
    }
    t.start()
    t
  }
  
  
  val a1 = new Account(500000)
  val a2 = new Account(700000)
  val t = startThread(a1,a2,150000)
  val s = startThread(a2,a1,150000)

  t.join()
  s.join()
}
