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
      
      def merge2(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, a) => ys
        case (a, Nil) => xs
        case (x :: xxs, y :: yys) => if (lt(x, y)) x :: merge2(xxs, ys) else y :: merge2(xs, yys)
      }

      val (fst, snd) = xs splitAt (n)

      merge2(msort(fst)(lt), msort(snd)(lt))
    }

  }





  def msort2[T](xs: List[T])(implicit  ord:Ordering[T]): List[T] = {
    val n = xs.length / 2

    if (n == 0) xs
    else {

      def merge2(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, a) => ys
        case (a, Nil) => xs
        case (x :: xxs, y :: yys) => if (ord.lt(x, y)) x :: merge2(xxs, ys) else y :: merge2(xs, yys)
      }

      val (fst, snd) = xs splitAt (n)

      merge2(msort2(fst), msort2(snd))
    }

  }





  val l = List(5, 4, 3, 2, 1, 7)
  show(l)
  val l2 = List(6, 7, 8, 9, 10)
  show(l2)
  show(msort(l)( (x,y) => x<y))
  
  val fruits = List("Apple", "Pineaple", "Banana")
  show(msort(fruits)( (x,y) => x.compareTo(y) <0))


  show(msort2(l)(Ordering.Int))

  show(msort2(fruits)(Ordering.String))



  show(msort2(l))

  show(msort2(fruits))
}



