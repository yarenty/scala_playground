package com.yarenty.week5

/**
  * Created by yarenty on 27/05/2017.
  */
object MergeSortList extends App {


  def show[T](l: List[T]) = {
    println(l.mkString(","))
  }


  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2

    if (n == 0) xs
    else {
      def merge(xs: List[Int], ys: List[Int]): List[Int] = xs match {
        case Nil => ys
        case z :: zs => ys match {
          case Nil => xs
          case v :: vs =>
            if (z < v) z :: merge(zs, ys)
            else v :: merge(xs, vs)
        }

      }

      def merge2(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
        case (Nil, a) => ys
        case (a, Nil) => xs
        case (x :: xxs, y :: yys) => if (x < y) x :: merge(xxs, ys) else y :: merge(xs, yys)
      }

      val (fst, snd) = xs splitAt (n)

      merge2(msort(fst), msort(snd))
    }

  }


  val l = List(5, 4, 3, 2, 1, 7)
  show(l)
  val l2 = List(6, 7, 8, 9, 10)
  show(l2)
  show(msort(l))

}



