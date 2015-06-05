package com.yarenty.ninety_nine.p06_palindrome

/**
 * Created by yarenty on 05/06/15.
 */
object Palindrome {


  def main(args: Array[String]) {
    val l = List('a, 'b, 'b, 'a)

    if (l == l.reverse) println("PALINDROME: " + l)
  }

}
