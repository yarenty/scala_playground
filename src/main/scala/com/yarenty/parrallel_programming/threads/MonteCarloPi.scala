package com.yarenty.parrallel_programming.threads

import scala.util.Random
import com.yarenty.parrallel_programming.common._
/**
  * Created by yarenty on 25/07/2017.
  */
object MonteCarloPi extends App {

  //sequential
  def mcCount(iter: Int): Int = {
    val randomX = new Random
    val randomY = new Random
    var hits = 0

    for (i <- 0 until iter) {
      val x = randomX.nextDouble
      val y = randomY.nextDouble

      if (x * x + y * y < 1) hits = hits + 1
    }
    
    hits
  }

  def monteCarloPiSeq(iter:Int): Double = 4.0 * mcCount(iter)/iter 
  val t0 = System.currentTimeMillis
  println(monteCarloPiSeq(100000))
  println(System.currentTimeMillis - t0)
  
  def monteCarloPiPar(iter:Int):Double = {
    val ((pi1,pi2),(pi3,pi4)) = parallel (
      parallel (mcCount(iter/4), mcCount(iter/4)),
      parallel (mcCount(iter/4), mcCount(iter - 3 * (iter/4))))

      4.0 * (pi1 + pi2+pi3+pi4) /iter
    
  }
  val t1 = System.currentTimeMillis
  println(monteCarloPiPar(100000))
  println(System.currentTimeMillis - t1)
  
}
