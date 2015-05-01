package com.yarenty.files

import java.io.File

/**
 * Created by yarenty on 01/05/15.
 */
object DisplayWithFilter extends App {

  val filesHere = (new File(".")).listFiles

  println(" filtering inside head of for:")
  for (file <- filesHere if !file.getName.contains("git"))
    println(file)

  println(" and filtering inside body of for:")
  for (file <- filesHere)
    if (file.getName.contains("git"))
      println(file)


  println(" filtering inside head of for and no directories :")
  for (file <- filesHere
       if file.isFile
       if !file.getName.contains("git"))
    println(file)


}
