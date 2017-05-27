package com.yarenty

/**
  * Created by yarenty on 24/05/2017.
  */
package object arrays {

  implicit class ArrayOps(val self: Array[Int]) {
    def -(that: Array[Int]): Array[Int] = {
      for (i <- self.indices) self(i) = self(i) - that(i)
      self
    }


    def +(that: Array[Int]): Array[Int] = (self zip that) map { case (x, y) => x + y }

  }

}
