package com.yarenty.traits

/**
 * Created by yarenty on 07/05/15.
 */

class Point(val x: Int, val y: Int)

trait Rectagular {

  def topLeft: Point

  def bottomRight: Point

  def left = topLeft.x

  def right = bottomRight.x

  def width = right - left

}

abstract class Component extends Rectagular {

}

class Rectangle (val topLeft: Point, val bottomRight: Point) extends Rectagular {

}

object Rectanglea extends App {

  val r = new Rectangle(new Point(1, 1), new Point(10, 10))

  println(r.left)
  println(r.right)
  println(r.width)

}
