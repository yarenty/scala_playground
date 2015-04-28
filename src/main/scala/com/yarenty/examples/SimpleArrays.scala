package com.yarenty.examples

/**
 * Created by yarenty on 28/04/15.
 */
object SimpleArrays {

  def main(args: Array[String]) {

    //simple array initialization
    val numNames = Array("one", "two", "three")

    // and simple print
    numNames.foreach(println)

    // consciese type
    val fullNums: Array[String] = new Array[String](3)
    fullNums(0) = "full One" //arrays are mutable!
    fullNums.update(1, "full Two")
    fullNums(2) = "full Three"

    for (i <- 0 to 2) {
      println(fullNums(i))
    }

  }
}
