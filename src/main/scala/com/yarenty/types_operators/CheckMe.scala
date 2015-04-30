package com.yarenty.types_operators

/**
 * Created by yarenty on 30/04/15.
 */
object CheckMe extends App {

  val hex = 0xcafebabe

  println("val hex = 0xcafebabe :" + hex)

  // scala 2.11 - no more octal ;-(
  //val oct = 0777
  //println(oct)

  val lon = 0xcafebabeL
  println("val lon = 0xcafebabeL :" + lon)

  val little: Short = 367
  println("val little: Short = 367 :" + little)

  val b: Byte = 38
  println("val b: Byte = 38 :" + b)

  val big = 123E45
  println("val big = 123E45 :" + big)

  val small = 123.45f
  println("val small = 123.45f :" + small)

  val a = 'A'
  println("val a = 'A' :" + a)

  val d = '\u0044'
  println("val d = \'\\u0044\' :" + d)

  // works even if your IDE says no ;-)
  // @formatter:off - ps: it's disable  code formatter
  val B\u0041\u0044 = 1
  println("val B\\u0041\\u0044 = 1; println(BAD) : " + BAD)
  // @formatter:on

  val backslash = '\\'
  println("val backslash = \'\\\\\' :" + backslash)


  println(
    """|Wecome!
      |To the world of scala.
      |..yes its cool
      |...
    """.stripMargin)

  val s = 'aSymbol
  println("val s = \'aSymbol :" + s)
  println("s.name :" + s.name)

  val hello = "Hello World!"
  print("val hello = \"Hello World!\"; hello indexOf \'o\' :")
  println(hello indexOf 'o')
  print("hello indexOf (\'o\',5) :")
  println(hello indexOf('o', 5))


  print("(2.0).unary_- :")
  println((2.0).unary_-)

  print("math.IEEEremainder(11.0,4.0) :")
  println(math.IEEEremainder(11.0, 4.0))


}
