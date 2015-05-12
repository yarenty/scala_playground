package com.yarenty.lists

/**
 * Created by yarenty on 12/05/15.
 */
object Basics {


  def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))

  def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)

  def isort2(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert2(y, isort2(ys))
  }

  def insert2(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs
    else y :: insert2(x, ys)
  }

  def add[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case Nil => ys
    case x :: xs2 => x :: add(xs2, ys)
  }


  def main(args: Array[String]) {
    val nums = 1 :: 2 :: 3 :: 4 :: 5 :: 6 :: Nil

    println(nums.head)
    println(nums.tail)
    println(nums.tail.head) //second

    println(isort(nums))

    val nums2 = 9 :: 5 :: 2 :: 1 :: 6 :: 5 :: 2 :: 0 :: Nil
    println(isort(nums2))
    println(isort2(nums2))

    println(nums ::: nums2)

    println(add(nums, nums2))
    println(add(nums, nums2).length)


    println(nums.last)
    println(nums.init) //without last one
    println(nums.init.last) //second last

    println(nums.reverse)

    println("take:" + (nums take 3))
    println("drop:" + (nums drop 3))
    println("splitAt:" + (nums splitAt 3))

    println("indices:" + (nums.indices))

    println("zip:" + (nums zip nums2))

    println("zip With index:" + (nums2.zipWithIndex))

    println(nums mkString("<", ",", ">"))


  }

}
