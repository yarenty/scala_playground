package com.yarenty.queries

/**
  * Created by yarenty on 27/06/2017.
  */
case class Book(title: String, authors: List[String])

object Books extends App {

  val books: List[Book] = List(
    Book(title = "Structure and Interpretation of Computer Programs",
      authors = List("Abelson, Harald", "Sussman, Gerald J.")),
    Book(title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java",
      authors = List("Bloch, Joshua")),
    Book(title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")),
    Book(title = "Programming in Scala",
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")), 
      Book( title = "Effective Java 2 ",
        authors = List("Bloch, Joshua"))

  )


  val bTitles = for (b <- books; a <- b.authors if a startsWith "Bird") yield b.title
  println(bTitles mkString ";")
  
  val jav = for (b<- books if (b.title indexOf "Java") >= 0) yield b.title
  println(jav mkString ";")

// multiple books
  val dob = for {
    b1 <- books
    b2 <- books
    if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1==a2
  } yield a1
  println(dob mkString ";")


  // multiple books - no twice anymore
  val dob2 = for {
    b1 <- books
    b2 <- books
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1==a2
  } yield a1
  println(dob2 mkString ";")


  // multiple books - no twice anymore
  val dob3 = {
    for {
      b1 <- books
      b2 <- books
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1
  }.distinct
  println(dob3 mkString ";")


  
  
  
  val books2: Set[Book] = Set(
    Book(title = "Structure and Interpretation of Computer Programs",
      authors = List("Abelson, Harald", "Sussman, Gerald J.")),
    Book(title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java",
      authors = List("Bloch, Joshua")),
    Book(title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")),
    Book(title = "Programming in Scala",
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")),
    Book( title = "Effective Java 2 ",
      authors = List("Bloch, Joshua"))

  )


  // multiple books
  val dob4 = for {
    b1 <- books2
    b2 <- books2
    if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1==a2
  } yield a1
  println(dob4 mkString ";")

  

}
