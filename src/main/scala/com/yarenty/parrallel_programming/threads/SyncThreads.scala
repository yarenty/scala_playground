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

class AccountDF(private var amount: Int = 0) {
  private val x = new AnyRef {}
  private var suid = 0L

  def getSyncUniqueId(): Long = x.synchronized {
    suid = suid + 1
    suid
  }

  val uid = getSyncUniqueId()

  private def lockAndTransfer(target: AccountDF, n: Int) =
    this.synchronized {
      target.synchronized {
        this.amount -= n
        target.amount += n
      }
    }

  def transfer(target: AccountDF, n: Int) =
    if (this.uid < target.uid) this.lockAndTransfer(target, n)
    else target.lockAndTransfer(this, -n)

}


object SyncThreads extends App {


  def startThread(a: Account, b: Account, n: Int) = {
    val t = new Thread {
      override def run(): Unit = {
        for (i <- 0 until n) {
          a.transfer(b, 1)
        }
      }
    }
    t.start()
    t
  }


//  val a1 = new Account(500000)
//  val a2 = new Account(700000)
//  val t = startThread(a1, a2, 15000)
//  val s = startThread(a2, a1, 15000)

  //deadlock
  //  t.join()
  //  s.join()

  println("deadlock")

  def startThread2(a: AccountDF, b: AccountDF, n: Int) = {
    val t = new Thread {
      override def run(): Unit = {
        for (i <- 0 until n) {
          a.transfer(b, 1)
        }
      }
    }
    t.start()
    t
  }


  println("start")
  val a1d = new AccountDF(500000)
  val a2d = new AccountDF(700000)
  val td = startThread2(a1d, a2d, 100)
  val sd = startThread2(a2d, a1d, 100)

  println("wait for finish")
  td.join()
  sd.join()

  println("finish")
  
}
