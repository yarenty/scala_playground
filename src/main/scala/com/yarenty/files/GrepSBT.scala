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

  //Two changes:
  // + there are {} instead () - so no need to use ; sign
  // + as trim is expensive - here is used only once
  def grepNoTrim(pattern: String) = {
    println(" filtering inside head and pattern matching :")
    for {file <- filesHere
         if file.isFile
         if file.getName.endsWith("sbt")
         line <- fileLines(file)
         trimmed = line.trim
         if trimmed.matches(pattern)
    }
      println(file + ": " + trimmed)
  }

  grepNoTrim(".*scala.*")


  println(" and here example of creating list/arrays from input")

  def filesSubList(files: Array[File]) = {
    for {file <- files
         if file.isFile
         if file.getName.endsWith("sbt")
    }
      yield file
  }

  println(filesSubList(filesHere))

  def getGreppedLines(pattern: String) = {
    println(" filtering inside head and pattern matching :")
    for {file <- filesHere
         if file.isFile
         if file.getName.endsWith("sbt")
         line <- fileLines(file)
         trimmed = line.trim
         if trimmed.matches(pattern)
    }
      yield trimmed
  }

  val lines = getGreppedLines(".*scala.*")

  lines foreach println



}
