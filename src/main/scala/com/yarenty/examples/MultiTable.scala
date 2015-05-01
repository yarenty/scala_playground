package com.yarenty.examples

/**
 * Created by yarenty on 01/05/15.
 */
object MultiTable extends App {

  def makeRowSeq(row: Int) =
    for (col <- 1 to 10) yield {
      val prod = (col * row).toString
      val padding = " " * (4 - prod.length)
      padding + prod
    }

  def makeRow(row: Int) = makeRowSeq(row).mkString

  def multiTable() = {
    val table = for (row <- 1 to 10) yield makeRow(row)

    table.mkString("\n")
  }

  println(multiTable)

}
