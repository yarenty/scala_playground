package com.yarenty.week5

/**
  * Created by yarenty on 27/05/2017.
  */
object MergeSortParametarizedList extends App {


  def show[T](l: List[T]) = {
    println(l.mkString(","))
  }


  def msort[T](xs: List[T])(lt: (T,T) =>Boolean): List[T] = {
    val n = xs.length / 2

    if (n == 0) xs
    else {
      
      def merge2(xs: List[T], ys: List[T])(lt: (T,T) =>Boolean): List[T] = (xs, ys) match {
        case (Nil, a) => ys
        case (a, Nil) => xs
        case (x :: xxs, y :: yys) => if (lt(x, y)) x :: merge2(xxs, ys)(lt) else y :: merge2(xs, yys)(lt)
      }

      val (fst, snd) = xs splitAt (n)

      merge2(msort(fst)(lt), msort(snd)(lt))(lt)
    }

  }


  val l = List(5, 4, 3, 2, 1, 7)
  show(l)
  val l2 = List(6, 7, 8, 9, 10)
  show(l2)
  show(msort(l)( (x,y) => x<y))

}



