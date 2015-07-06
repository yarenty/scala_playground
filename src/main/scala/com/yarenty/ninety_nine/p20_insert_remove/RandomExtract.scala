package com.yarenty.ninety_nine.p20_insert_remove


import scala.util.Random.nextInt

/**
 * Input
 * List('a,'b,'c,'d,'e,'f,'g,'h)
 *
 *
 * randomextract:
 * Output
 * List('d,'a,'g)
 * Created by yarenty on 06/07/15.
 */
object RandomExtract {

  def extract[A](n: Int, l: List[A]): List[A] = {
    var ll = l
    //var z: (List[A],A) = (Nil, null)
    var out: List[A] = Nil
    var x = 0
    for (x <- 1 to n) {
      val z = removeKth.remove(nextInt(ll.size), ll)
      ll = z._1
      out = out ::: List(z._2)
    }
    out
  }


  def extractRec[A](n: Int, l: List[A]): List[A] = {
    if (n == 0) Nil
    else {
      val o = removeKth.remove(nextInt(l.size), l)
      List(o._2) ::: extractRec(n - 1, o._1)
    }
  }


  def main(args: Array[String]) {
    val list = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h)

    println(extract(3, list))
    println(extractRec(3, list))
  }


}
