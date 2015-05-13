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


  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x :: xs1, y :: ys1) => if (less(x, y)) x :: merge(xs1, ys)
      else y :: merge(xs, ys1)
    }

    val n = xs.length / 2

    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }

  }


  def matrixWithZeroRow (m: List[List[Int]]) = m exists (row => row forall (_ == 0))

  def sumList(xs: List[Int]): Int = (0 /: xs)(_ + _)

  def productList(xs: List[Int]): Int = (1 /: xs)(_ * _)

  def reverseLeft[T](xs: List[T]) = (List[T]() /: xs) { (ys, y) => y :: ys }


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


    println(msort((x: Int, y: Int) => x <= y)(nums ::: nums2))

    val intSort = msort((x: Int, y: Int) => x < y) _
    val reverseIntSort = msort((x: Int, y: Int) => x > y) _

    println(intSort(nums ::: nums2))
    println(reverseIntSort(nums ::: nums2))


    println(nums map (_ + 66))
    println(nums map (_.toString.reverse.mkString("{", "|", "}")))

    println(List.range(1, 5) flatMap (i => List.range(1, i) map (j => (i, j))))

    println(for (i <- List.range(1, 5); j <- List.range(1, i)) yield (i, j))

    var sum = 0
    List.range(1, 5) foreach (sum += _)
    println("SUM:" + sum)

    println(List.range(1, 10) filter (_ % 2 == 0))
    println("partition:")
    println(List.range(1, 10) partition (_ % 2 == 0))


    println(List(1, 2, 3, 4, -1, -3, 5, 4, 4, 6, 4, 2, 23, 34, 4, 5, -3, 0) takeWhile (_ > 0))
    println(List(1, 2, 3, 4, -1, -3, 5, 4, 4, 6, 4, 2, 23, 34, 4, 5, -3, 0) dropWhile (_ > 0))
    println(List(1, 2, 3, 4, -1, -3, 5, 4, 4, 6, 4, 2, 23, 34, 4, 5, -3, 0) span (_ > 0))

    println(sumList(nums))
    println(productList(nums))

    val l = List("This", "is", "the", "end", ":-)")
    println((l.head /: l.tail)(_ + " " + _))
    println((l :\ "")(_ + " " + _))


    println(reverseLeft(nums))

    // and much simpler sort ;-)
    println((nums ::: nums2).sortWith(_ < _))

    println(List.fill(4)("hello world"))
    println(List.fill(2, 3)('x'))

    println(List.tabulate(5)(n => n * n * n))

    println(List.tabulate(5, 5)(_ * _) mkString ("\n"))

  }

}
