package com.yarenty.oredering


import math.Ordering
import scala.util.Sorting

/**
  * Created by yarenty on 02/12/2016.
  * (C)2015 SkyCorp Ltd.
  */
object OrderingTest {


  val list = "aaa" :: "ddd" :: "eeee" :: "bbb" :: "hhh" :: Nil


  def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (ord.compare(x, y) < 0) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(ys), msort(zs))
    }

    // Sorting.stableSort[T](xs)
  }


  def main(args: Array[String]): Unit = {

    println(list.sorted)
    println(msort(list)(Ordering.String))
    println(msort(list))

  }

}
