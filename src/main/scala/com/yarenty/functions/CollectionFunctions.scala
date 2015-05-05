package com.yarenty.functions

/**
 * Created by yarenty on 05/05/15.
 */
object CollectionFunctions extends App {

  def containsNeg(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums) if (num < 0) exists = true
    exists
  }


  println(containsNeg(List(1, 2, 3, 4)))
  println(containsNeg(List(1, -2, 3, 4)))


  def containsNeg2(nums: List[Int]) = nums.exists(_ < 0)


  println(containsNeg2(List(1, 2, 3, 4)))
  println(containsNeg2(List(1, -2, 3, 4)))


  def containsOdd(nums: List[Int]): Boolean = {
    var exist = false
    for (num <- nums)
      if (num % 2 == 1)
        exist = true
    exist
  }


  println(containsOdd(List(1, 2, 3, 4)))

  def containsOdd2(nums: List[Int]) = nums.exists(_ % 2 == 1)

  println(containsOdd2(List(1, 2, 3, 4)))

}
