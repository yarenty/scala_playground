package com.yarenty.files

import java.io.File

import scala.io.Source

/**
 * Nested for loops + pattern.
 * Created by yarenty on 01/05/15.
 */
object GrepSBT extends App {

  val filesHere = (new File(".")).listFiles


  def fileLines(file: File) = Source.fromFile(file).getLines().toList

  def grep(pattern: String) = {
    println(" filtering inside head of for and no directories :")
    for (file <- filesHere
         if file.isFile
         if file.getName.endsWith("sbt");
         line <- fileLines(file)
         if line.trim.matches(pattern)
    )
      println(file + ": " + line.trim)
  }

  grep(".*scala.*")

}
