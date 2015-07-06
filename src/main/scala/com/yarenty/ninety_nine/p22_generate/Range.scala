package com.yarenty.ninety_nine.p22_generate

/**
 * Input:
 * rangeBuild(4, 9)
 *
 *
 * Output:
 * List(4, 5, 6, 7, 8, 9)
 *
 *
 * Created by yarenty on 03/07/15.
 */
object Range {

  def rangeBuild(start: Int, end: Int): List[Int] = List.range(start, end + 1)


  def rangeRecursive(start: Int, end: Int): List[Int] = {
    if (end < start) Nil
    else start :: rangeRecursive(start + 1, end)
  }

  def rangeTailRecursive(start: Int, end: Int): List[Int] = {
    def rangeR(end: Int, result: List[Int]): List[Int] = {
      if (end < start) result
      else rangeR(end - 1, end :: result)
    }
    rangeR(end, Nil)
  }


  def main(args: Array[String]) {
    println(rangeBuild(4, 9))
    println(rangeRecursive(4, 9))
    println(rangeTailRecursive(4, 9))
  }

}
