package com.yarenty.util.resource

import java.io.InputStream

import sun.net.www.content.text.PlainTextInputStream

/**
 * Created by yarenty on 11/09/2015.
 */
object ResourceReader {


  def main(args: Array[String]) {


    val resio = getClass.getResource("/test_resource.txt")

    println(resio)
    println(resio.getFile)
    println(resio.getContent)
    val input:InputStream = resio.openStream
    val lines = scala.io.Source.fromInputStream( input ).getLines
    lines.foreach(println)
  }

}
