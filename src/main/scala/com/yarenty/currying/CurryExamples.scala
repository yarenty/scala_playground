package com.yarenty.currying

/**
  * Created by yarenty on 16/05/2017.
  */

object Main extends App {


  def sumInts(a: Int, b: Int): Int = {
    if (a > b) 0 else a + sumInts(a + 1, b)
  }


  def cube(x: Int): Int = x * x * x


  def sumCubes(a: Int, b: Int): Int =
    if (a > b) 0 else cube(a) + sumCubes(a + 1, b)


  println(sumInts(1, 5))
  println(sumCubes(1, 5))


  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)


  def sumInts2(a: Int, b: Int) = sum(id, a, b)

  def id(x: Int): Int = x

  def fact(x: Int): Int = if (x == 0) 1 else fact(x - 1)

  def sumCubes2(a: Int, b: Int) = sum(cube, a, b)

  def sumFactorials(a: Int, b: Int) = sum(fact, a, b)


  println(sumInts2(1, 5))
  println(sumCubes2(1, 5))
  println(sumFactorials(1, 5))


  def sumInts3(a: Int, b: Int) = sum(x => x, a, b)


  def sumCubes3(a: Int, b: Int) = sum(x => x * x * x, a, b)

  println(sumInts3(1, 5))
  println(sumCubes3(1, 5))

  def sum2(f: Int => Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    }

    loop(a, 0)
  }


  def sumInts4(a: Int, b: Int) = sum2(x => x)(a, b)


  def sumCubes4(a: Int, b: Int) = sum2(x => x * x * x)(a, b)


  println(sumInts4(1, 5))
  println(sumCubes4(1, 5))


  //it will return another function
  def currySum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)

    sumF
  }


  def sumInts5 = currySum(x => x)

  def sumCubes5 = currySum(x => x * x * x)

  println(sumInts5(1, 5))
  println(sumCubes5(1, 5))


  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product(f)(a + 1, b)
  }

  println(product(x => x * x)(3, 4))
  println(product(fact)(1, 5))

  def factProd(n: Int) = product(x => x)(1, n)

  println(factProd(5))


  def combined(f: Int => Int)(init: Int)(comb: (Int, Int) => Int)(a: Int, b: Int): Int = {
    if (a > b) init
    else comb(f(a), combined(f)(init)(comb)(a + 1, b))
  }

  println(combined(x => x * x)(1)((x, y) => x * y)(3, 4))


  def mapReduce(f: Int => Int, comb: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else comb(f(a), mapReduce(f, comb, zero)(a + 1, b))
  }

  println(combined(x => x * x)(1)((x, y) => x * y)(3, 4))

  def product2(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)

  println(product2(x => x * x)(3, 4))
}  


