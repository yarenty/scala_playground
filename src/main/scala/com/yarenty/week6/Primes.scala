package com.yarenty.week6

/**
  * Created by yarenty on 27/05/2017.
  */
object Primes extends App {


  def isPrime(n: Int): Boolean = {
    (2 until n) forall (d => n % d != 0)
  }

  println(isPrime(2))
  println(isPrime(3))
  println(isPrime(4))
  println(isPrime(6))
  println(isPrime(7))


  val n = 7

  // list of pairs where sum is prime ans first is less than second
  val se = (1 until n) map (i =>
    (1 until i) map (j => (i, j)))
  println(se)

  //  val xx:Seq[Int] = se.foldRight(Seq[Int]())((x,y) => x ++ y)

  val xss = se.flatten

  println(xss)


  val se2 = (1 until n) flatMap (i =>
    (1 until i) map (j => (i, j)))
  println(se2)


  val out = se2.filter(p => isPrime(p._1 + p._2))
  println(out)


  //forexpressions

  def scalaProduct(xs: List[Int], ys: List[Int]): Int = {
    (for ((x, y) <- xs zip ys) yield x * y).sum
  }

  val l = List(1, 2, 3)

  println(scalaProduct(l, l))


  val ses = for {i <- 1 until n; j <- 1 until i; if isPrime(i + j)} yield (i, j)

  println(ses)

}
