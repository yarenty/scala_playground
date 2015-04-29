package com.yarenty.classes

import scala.collection.mutable.Map

/**
 * Created by yarenty on 29/04/15.
 */
class ChecksumAccumulator {
  private var sum = 0

  def add(b: Byte): Unit = {
    sum += b
  }

  def checksum(): Int = {
    ~(sum & 0xFF) + 1
  }
}

object ChecksumAccumulator {

  private val cache = Map[String, Int]()

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs) // yes, I need mutable map for this one
      cs
    }


  def main(args: Array[String]) {
    val acc = new ChecksumAccumulator
    acc.sum = 5

    println(acc.sum)

  }
}


