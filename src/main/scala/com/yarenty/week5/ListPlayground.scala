package com.yarenty.week5


/**
  * Created by yarenty on 27/05/2017.
  */
object ListPlayground extends App {

  def show[T](l: List[T]) = {
    println(l.mkString(","))
  }

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case Nil => ys
    case z :: zs => z :: concat(zs, ys)
  }

  def reverse[T](xs: List[T]): List[T] = xs match {
    case Nil => xs
    case y :: ys => reverse(ys) ++ List(y)
  }

  def removeAt[T](xs: List[T], n: Int): List[T] = xs match {
    case Nil => xs
    case y :: ys => if (n == 0) removeAt(ys, n - 1)
    else y :: removeAt(ys, n - 1)
  }


  def removeAt2[T](xs: List[T], n: Int): List[T] = (xs take n) ::: (xs drop n + 1)


  val l = List(1, 2, 3, 4, 5)
  show(l)
  val l2 = List(6, 7, 8, 9, 10)
  show(l2)

  show(l.updated(3, 66))
  show(concat(l, l2))
  show(reverse(l))
  show(removeAt(l, 3))
  show(removeAt(l, 8))
  show(removeAt2(l, 3))
  show(removeAt2(l, 8))


  //MAP

  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  }

  def map[T,U](xs: List[T])(f: T => U): List[U] = xs match {
    case Nil => Nil
    case y :: ys => f(y) :: map(ys)(f)
  }

  def scaleList2(xs: List[Double], factor: Double): List[Double] = map(xs)(x => x * factor)

  def squareList(xs: List[Double]): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * y :: squareList(ys)
  }

  def squareList2(xs: List[Double]): List[Double] = map(xs)(x => x * x)


  val dl = List(1.0, 4.0, -8.0, 1.2, 453.3)
  show(scaleList(dl, 2.3))
  show(scaleList2(dl, 2.3))


  show(squareList(dl))
  show(squareList2(dl))


  //FILTER

  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
  }

  def posElems2(xs: List[Int]): List[Int] = xs filter( x=> x>0)

 val di = map(dl)(x => x.toInt)
  show(posElems(di))
  show(posElems2(di))
  
  val (p,n) = di.partition(x =>x>0)
  show(p)
  show(n)
  show(di.takeWhile(x=>x>0))
  show(di.dropWhile(x=>x>0))
  
  
  
  
  def pack[T](xs:List[T]):List[List[T]] = xs match {
    case Nil => Nil
    case y::ys =>
      val (l,r) =  xs.partition(z=>z==y)
      l :: pack(r)
  }

  def pack2[T](xs:List[T]):List[List[T]] = xs match {
    case Nil => Nil
    case y::ys =>
      val (l,r) =  xs.span(z=>z==y)
      l :: pack2(r)
  }
  
  
  def encode[T](xs: List[List[T]]):List[(T,Int)] = xs match{
    case Nil => Nil
    case y::ys => (y.head,y.length) :: encode(ys)
  }


  def encode2[T](xs: List[T]):List[(T,Int)] =  pack2(xs) map (ys => (ys.head, ys.length))



  val pp = List(1,2,1,1,1,3,3,3,4,4,4,1)
  show(pack(pp))
  show(pack2(pp))
  show(encode(pack2(pp)))
  show(encode2(pp))

}
