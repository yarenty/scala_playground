package com.yarenty.week6

import scala.io.Source
import java.io.File

/**
  * Created by yarenty on 29/05/2017.
  */
object PhoneToWords extends App {

  def subFile(file: File, children: String*) = {
    children.foldLeft(file)((file, child) => new File(file, child))
  }

  def resourceAsStreamFromSrc(resourcePath: List[String]): Option[java.io.InputStream] = {
    val classesDir = new File(getClass.getResource(".").toURI)
    val projectDir = classesDir.getParentFile.getParentFile.getParentFile.getParentFile
    val resourceFile = subFile(projectDir, ("src" :: "main" :: "resources" :: resourcePath): _*)
    val rFile = subFile(projectDir, ("classes" :: resourcePath): _*)
    if (resourceFile.exists)
      Some(new java.io.FileInputStream(resourceFile))
    else if (rFile.exists) {
      Some(new java.io.FileInputStream(rFile))
    }
    else  None
  }

  val dictionaryPath = List("linuxwords.txt")

  val wordsIn = Option {
    getClass.getResourceAsStream(dictionaryPath.mkString("/"))
  } orElse {
    resourceAsStreamFromSrc(dictionaryPath)
  } getOrElse {
    sys.error("Could not load word list, dictionary file not found")
  }

  val words:List[String] = 
  try {
    val s = io.Source.fromInputStream(wordsIn)
    s.getLines.toList filter( word => word forall(p => p.isLetter))
  } catch {
    case e: Exception =>
      println("Could not load word list: " + e)
      throw e
  } finally {
    wordsIn.close()
  }


  val mnemonics = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI",
    '5' -> "JKL", '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV",
    '9' -> "WXYZ")


  val charCode: Map[Char, Char] =
    for ((digit, str) <- mnemonics; ltr <- str) yield ltr -> digit


  def wordCode(word: String): String =
    word.toUpperCase map charCode


  def wordsForNum: Map[String,Seq[String]] = 
  words groupBy wordCode withDefaultValue Seq()
  
  def phoneNumber = "7225247386"

  println(wordCode("Java"))

  
    def encode(p: String): Set[List[String]] = {

      if (p.isEmpty) Set(List())
      else {
        for {
          split <- 1 to p.length
          word <- wordsForNum(p take split)
          rest <- encode(p drop split)
        } yield word :: rest
      }.toSet
    
    }
  
  def translate ( number:String):Set[String] = {
    encode(number) map ( _ mkString " ")
  }


    println(translate(phoneNumber))

}
