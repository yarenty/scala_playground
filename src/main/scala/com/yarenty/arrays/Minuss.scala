package com.yarenty.arrays

/**
  * Created by yarenty on 24/05/2017.
  */
object Minuss extends App {




  //  implicit class ArrayOps(val self:Array[Int]){
  //    def - (that:Array[Int]):this.type = {
  //      for( i <- self.indices) {
  //        self.update(i, self(i) - that(i))
  //      }
  //      this
  //    }
  //  }


  val t1 = Array(10, 11, 12, 13, 14)
  val t2 = Array(3, 2, 1, 0, 5)
  val t3 = Array(-1, 3, 2, 4, 8)


  val res = t1 - t2 - t3

  println(res.mkString(","))
  
  val minus = (t1 zip t2).map{ case (x,y) => x - y}
 println((t1-t2).mkString(","))
 println(minus.mkString(","))


  println((t1+t2).mkString(","))
  
  println((Array(1,1)+t1+t2+Array(1,2,3)).mkString(","))
}
