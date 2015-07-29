package com.yarenty.facebooks

/**
 *
 * Problem:
 * Read number, e.g. 100 -&gt; one hundred, 113 -&gt; one   hundred thirteen
 *
 *
 * Created by yarenty on 22/07/2015.
 */
object ReadNumbers {

  def intToString(n: Int): String = {


    def readSingle(n: Int): String = n match {
      case x if (x >= 90) => "nighty " + readSingle(x - 90)
      case x if (x >= 80) => "eighty " + readSingle(x - 80)
      case x if (x >= 70) => "seventy " + readSingle(x - 70)
      case x if (x >= 60) => "sixty " + readSingle(x - 60)
      case x if (x >= 50) => "fifty " + readSingle(x - 50)
      case x if (x >= 40) => "fourty " + readSingle(x - 40)
      case x if (x >= 30) => "thirty " + readSingle(x - 30)
      case x if (x >= 20) => "twenty " + readSingle(x - 20)
      case x if (x == 19) => "nighteen " ß
      case x if (x == 18) => "eighteen "
      case x if (x == 17) => "seventeen "
      case x if (x == 16) => "sixteen "
      case x if (x == 15) => "fiveteen "
      case x if (x == 14) => "fourteen "
      case x if (x == 13) => "thirteen "
      case x if (x == 12) => "twelve "
      case x if (x == 11) => "eleven "
      case x if (x == 10) => "ten "
      case x if (x == 9) => "night "
      case x if (x == 8) => "eight "
      case x if (x == 7) => "seven "
      case x if (x == 6) => "six "
      case x if (x == 5) => "five "
      case x if (x == 4) => "four "
      case x if (x == 3) => "tree "
      case x if (x == 2) => "two "
      case x if (x == 1) => "one "
      case x if (x == 0) => ""

    }

    def read(n: Int): String = {
      if (n > 1000000) read(n / 1000000) + "million " + read(n % 1000000)
      else
      if (n > 1000) read(n / 1000) + "thousand " + read(n % 1000)
      else
      if (n > 100) readSingle(n / 100) + "hundred " + readSingle(n % 100)
      else
        readSingle(n % 100)
    }

    if (n == 0) "zero"
    else if (n < 0) "minus " + read(-n)
    else read(n)

  }

  def main(args: Array[String]) {

    println(intToString(113))
    println(intToString(-243))
    println(intToString(0))

    println(intToString(2450)
    )
    println(intToString(24524))
    println(intToString(934658))
    println(intToString(2934658))
    println(intToString(203934658))

  }

}
