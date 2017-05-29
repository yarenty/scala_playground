package com.yarenty.week6

/**
  * Created by yarenty on 29/05/2017.
  */
object Anagrams extends App {


  val w = "abcd"
  val m = w.toLowerCase
  //val mm = w.map(a => (a,1))

  println(m)
  val g = m.groupBy(z => z)

  println(g)

  println(g.map(x => (x._1, x._2.length)).toList.sorted) //By(_._1))

  //println( for (z <- g) yield (z._1,z._2.length))


  def showMap(c: String, m: Map[String, String]): String = m.get(c) match {
    case Some(n) => n
    case None => "missing data"
  }


  val capitals = Map("US" -> "Washington", "PL" -> "Warsaw", "IE" -> "Dublin")

  println(showMap("US", capitals))
  println(showMap("andora", capitals))


  class Poly(val terms: Map[Int, Double]) {
    
      
    def adjust (term:(Int,Double)):(Int,Double) = {
      val (exp,coeff) = term
      terms get exp match {
        case Some(c1) => exp -> (coeff + c1)
        case None => exp -> coeff
      }
    }
    
    def +(other: Poly) = new Poly(terms ++ (other.terms map adjust))
      
      
    override def toString: String = 
    
      (for ((exp,coeff) <- terms.toList.sorted.reverse) yield coeff+"x^"+ exp ) mkString " + "
  }

  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
  val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))

  println(p1 + p2)

}
