package com.yarenty.streams

/**
  * Created by yarenty on 17/07/2017.
  */
object Primes {


  def secondPrime(from: Int, to: Int) = nthPrime(from, to, 2)

  def nthPrime(from: Int, to: Int, n: Int): Int =
    if (from >= to) throw new Error("no prime")
    else if (isPrime(from))
      if (n == 1) from else nthPrime(from + 1, to, n - 1)
    else nthPrime(from + 1, to, n)


  def isPrime(n: Int): Boolean = {
    (2 until n) forall (d => n % d != 0)
  }


  def from(n: Int): Stream[Int] = n #:: from(n + 1)


  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head != 0))
  

  
  def sqrtStream(x:Double):Stream[Double] = {
    def improve(guess:Double) = (guess + x/guess) /2
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  }
  
  
  def isGoodEnough(guess:Double, x:Double) = 
    math.abs((guess*guess -x)/x) <0.0001
  
  
  
  def main(args: Array[String]): Unit = {

    var t = System.currentTimeMillis
    val s = secondPrime(1000, 10000)
    var tt = System.currentTimeMillis
    println(s)
    println(tt - t)


    t = System.currentTimeMillis
    val sp = ((1000 to 10000) filter isPrime) (1)
    tt = System.currentTimeMillis
    println(sp)
    println(tt - t)

    t = System.currentTimeMillis
    val sps = ((1000 to 10000).toStream filter isPrime) (1)
    tt = System.currentTimeMillis
    println(sps)
    println(tt - t)


    t = System.currentTimeMillis
    val sps2 = (Stream.range(1000, 10000) filter isPrime) (1)
    tt = System.currentTimeMillis
    println(sps2)
    println(tt - t)

    
    val allPrimes = sieve(from(2))
    
    println(allPrimes.take(10).toList)
    
    
    println(sqrtStream(4).take(10).toList)
    println((sqrtStream(4) filter (isGoodEnough(_, 4))).take(10).toList)


    t = System.currentTimeMillis
    val xs = from(1) map (_ * 1000)
    tt = System.currentTimeMillis
    println(xs)
    println(tt - t)

    t = System.currentTimeMillis
    val ys = from(1) filter (_ % 1000 == 0)
    tt = System.currentTimeMillis
    println(ys)
    println(tt - t)
    
    
    
    
  }
}



