package com.yarenty.util.resource

/**
 * Created by yarenty on 11/09/2015.
 */
object ResourceReader {


  def main(args: Array[String]) {


    val resio = getClass.getResource("/test_resource.txt")

    println(resio)
    println(resio.getFile)
  }

}
