package com.yarenty.facebooks

import scala.collection.mutable.HashMap

/**
 * Problem:
 * Given a sorted set of numbers, find the first sequence of   numbers that add up to a specified value.
 *
 *
 * Created by yarenty on 22/07/2015.
 */
object FindSequence {


  // Q: is it optional??
  def firstSeq(s: Array[Int], v: Int): List[Int] = {

    val out: HashMap[Int, List[Int]] = new HashMap[Int, List[Int]] //(v + 1)

    for (x <- s) {
      if (v - x == 0) return List(x) //single value
      for (i <- x to v) {
        if (out.get(i).orNull == null)
          out.put(i, List(x))
        else {
          if (out.get(i).orNull.foldLeft(0)(_ + _) + x <= v)
            out.put(i, out.get(i).orNull ::: List(x))
        }
        if (out.get(i).orNull.foldLeft(0)(_ + _) == v)
          return out.get(i).orNull
      }
    }
    return null

  }

  def main(args: Array[String]) {

    val seq1 = Array(9, 8, 7, 4, 3, 2, 1)
    val seq2 = Array(9, 8, 7, 3, 2, 1)
    val seq3 = Array(9, 8, 7, 2, 1)

    val value = 12

    println(firstSeq(seq1, value))
    println(firstSeq(seq2, value))
    println(firstSeq(seq3, value))

  }


}
