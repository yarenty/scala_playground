package com.yarenty.lists

/**
  * Created by yarenty on 02/12/2016.
  * (C)2015 SkyCorp Ltd.
  */
object ListTester {


  val list = 1 :: 2 :: 3 :: 4 :: Nil

  val listB = 2 :: 4 :: Nil

  def main(args: Array[String]): Unit = {

    println(list) //List(1, 2, 3, 4)
    println(list.length) //4
    println(list.last) //4
    println(list.init) //  List(1, 2, 3)
    println(list.take(2)) //List(1, 2)
    println(list.reverse) //List(4, 3, 2, 1)


    println(list.updated(2, 7)) //List(1, 2, 7, 4)

    println(list.indexOf(1)) //0
    println(list.contains(3)) //true


    println("FILTERING::")
    println(list.filter(x => x > 2)) //List(3, 4)
    println(list.filterNot(x => x > 2)) //List(1, 2)
    println(list.partition(x => x > 2)) //(List(3, 4),List(1, 2))

    println("TAKE WHILE::")
    println(list.takeWhile(x => x > 2)) //List()
    println(list.dropWhile(x => x > 2)) //List(1, 2, 3, 4)
    println(list.span(x => x > 2)) //(List(),List(1, 2, 3, 4))


    println(list.takeWhile(x => x <= 2)) //List(1, 2)
    println(list.dropWhile(x => x <= 2)) //List(3, 4)
    println(list.span(x => x <= 2)) //((List(1, 2),List(3, 4))


    println("REDUCE::")

    println(list.reduceLeft((x, y) => x + y)) //10
    println(list.foldLeft(0)((x, y) => x + y)) //10
    println(list.reduceLeft((x, y) => x * y)) //24
    println(list.foldLeft(0)((x, y) => x * y)) //0
    println(list.foldLeft(1)((x, y) => x * y)) //24

    println(list.reduceRight((x, y) => x + y)) //10
    println(list.foldRight(0)((x, y) => x + y)) //10
    println(list.reduceRight((x, y) => x * y)) //24
    println(list.foldRight(0)((x, y) => x * y)) //0
    println(list.foldRight(1)((x, y) => x * y)) //24

    println(list.exists(x => x > 3)) //true
    println(list.forall(x => x > 3)) //false
    println(list.forall(x => x > 0)) //true

    println("ZIPS, flatMap, sum, prod")
    println(list zip listB) //List((1,2), (2,4))
    println(list.flatMap(x => List(x * 2))) // List(2, 4, 6, 8)    - function must return sequence
    println(list.flatMap(x => List(x, x * x, x * x * x))) // List(1, 1, 1, 2, 4, 8, 3, 9, 27, 4, 16, 64)    - function must return sequence
    println(list sum) //10
    println(list product) //24
    println(list min) //1
    println(list max) //4


    println(List(list, listB) flatten) // List(1, 2, 3, 4, 2, 4)
    println((List(list, listB) flatten) distinct) // List(1, 2, 3, 4)

    println(list groupBy (x => x > 2)) //Map(false -> List(1, 2), true -> List(3, 4))


    println(0 +: list) // List(0, 1, 2, 3, 4)
    println(list :+ 5) // List(1, 2, 3, 4, 5)
  }


}
