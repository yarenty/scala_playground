package com.yarenty.files

import java.io.File

/**
 * Examples of accessing of files.
 * And additionally using different type fro generators.
 * Created by yarenty on 01/05/15.
 */
object Display extends App {

  val filesHere = (new File(".")).listFiles()

  println(" from filesHere GENERATOR:")
  for (file <- filesHere)
    println(file)


  println("simple: 1 to 4")
  for (i <- 1 to 4)
    println(i)

  println("another simple: 1 until 4")
  for (i <- 1 until 4)
    println(i)

  println("just like java indexing :-) getting 0 to filesHere.length-1:")
  for (i <- 0 to filesHere.length - 1)
    println(filesHere(i))

}
