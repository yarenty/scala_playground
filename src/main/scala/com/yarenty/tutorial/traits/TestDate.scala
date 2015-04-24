package com.yarenty.tutorial.traits

/**
 * Date created on top of Ord - other could be created just like that!
 * Created by yarenty on 24/04/15.
 */
object TestDate {

  def main(args: Array[String]) {
    val d1 = new Date(2015, 4, 24)
    val d2 = new Date(2014, 4, 24)
    val d3 = new Date(2015, 4, 22)
    val d4 = new Date(2015, 4, 28)
    val d5 = new Date(2015, 4, 24)
    println("d1::" + d1)
    println("d2::" + d2)
    println("d3::" + d3)
    println("d4::" + d4)
    println("d5::" + d5)

    println("d1>d2::" + (d1 > d2))
    println("d1>d4::" + (d1 > d4))
    println("d1>=d2::" + (d1 >= d2))
    println("d1<=d2::" + (d1 < d2))
    println("d1!=d5::" + (d1 != d5))
    println("d4<=d3::" + (d4 <= d3))
  }

}
