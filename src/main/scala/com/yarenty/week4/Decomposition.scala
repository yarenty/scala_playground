package com.yarenty.week4

/**
  * Created by yarenty on 23/05/2017.
  */
trait Expr {
  def isNumber: Boolean

  def isSum: Boolean

  def numValue: Int

  def leftOp: Expr

  def rigthOp: Expr

}


class Number(n: Int) extends Expr {
  override def isNumber: Boolean = true

  override def isSum: Boolean = false

  override def numValue: Int = n

  override def leftOp: Expr = throw new Error("number left op")

  override def rigthOp: Expr = throw new Error("number right op")
}