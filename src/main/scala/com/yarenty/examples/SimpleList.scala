package com.yarenty.examples

/**
 * Created by yarenty on 28/04/15.
 */
object SimpleList {

  def main(args: Array[String]) {

    val oneTwo = List(1, 2)
    val threeFour = List(3, 4)
    val oneTwoThreeFour = oneTwo ::: threeFour //list are immutable!

    println(oneTwo + " and " + threeFour + " were not mutated.")
    println("Thus, " + oneTwoThreeFour + " is a new list.")


    val twoThree = List(2, 3)
    val oneTwoThree = 1 :: twoThree

    println(" :: - cons prepends a new element to the beginning of an existing list => " + oneTwoThree)

    val anotherOneTwoThree = 1 :: 2 :: 3 :: Nil
    println(" :: another one => " + anotherOneTwoThree)

    lists()
  }


  def lists(): Unit = {

    val thrill = "Will" :: "fill" :: "until" :: Nil
    println("thrill =" + thrill)

    println("thrill(2) =" + thrill(2)) //until

    println("thrill.count(s =>s.length==4) =" + thrill.count(s => s.length == 4)) //2

    println("thrill.drop(2) = " + thrill.drop(2))

    println("thrill.dropRight(2) =" + thrill.dropRight(2))

    println("thrill.exists(s => s == \"until\") =" + thrill.exists(s => s == "until"))

    println("thrill.filter(s => s.length == 4) =" + thrill.filter(s => s.length == 4))

    println("thrill.forall(s => s.endsWith(\"l\")) =" + thrill.forall(s => s.endsWith("l")))

    println("thrill.foreach(s => print(s)) =");
    thrill.foreach(s => print(s))

    println("\nthrill.foreach(print) =")
    thrill.foreach(print)

    println("\nthrill.head =" + thrill.head)

    println("thrill.init =" + thrill.init)

    println("thrill.isEmpty =" + thrill.isEmpty)

    println("thrill.last =" + thrill.last)

    println("thrill.length =" + thrill.length)

    println("thrill.map(s => s +\"y\") =" + thrill.map(s => s + "y"))

    println("thrill.mkString(\", \") =" + thrill.mkString(", "))

    println("thrill.dropWhile(s => s.length ==4) =" + thrill.dropWhile(s => s.length == 4))

    println("thrill.reverse =" + thrill.reverse)

    println("thrill.sortWith((s,t) => s.charAt(0).toLower < t.charAt(0).toLower) =" +
      thrill.sortWith((s, t) => s.charAt(0).toLower < t.charAt(0).toLower))

    println("thrill.tail =" + thrill.tail)


  }


}
