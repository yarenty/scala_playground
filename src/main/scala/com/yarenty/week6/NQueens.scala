package com.yarenty.week6

import language.postfixOps
/**
  * Created by yarenty on 27/05/2017.
  */
object NQueens extends App {

  def queens(n: Int): Set[List[Int]] = {
    def placeQueens(k: Int): Set[List[Int]] =
      if (k == 0) Set(List())
      else
        for {
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
        } yield col :: queens

    def isSafe(col: Int, queens: List[Int]): Boolean = {
      val row = queens.length
      val queensWithRows = (row - 1 to 0 by -1) zip queens
      queensWithRows forall {
        case (r, c) => col != c && math.abs(col - c) != row - r
      }
    }


    placeQueens(n)
  }

  
  println(queens(4))
  
  
  
  def show (queens:List[Int]) = {
    val lines =  for (col <-queens.reverse) 
      yield Vector.fill(queens.length)("* ").updated(col,"X ").mkString
    "\n" + (lines mkString "\n")
  }
  
//  for (q <- queens(4)) println(show(q))
  
  
  println( (queens(4) map show) mkString "\n" )
  
  
  println( (queens(8) take 3 map show) mkString "\n" )
  
}
