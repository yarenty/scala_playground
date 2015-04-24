package com.yarenty.tutorial.traits

/**
 * Traits = interfaces that can contain code.
 * Ord is about making object comparable (for sorting etc..) aka: java Comparable.
 *
 *
 * Created by yarenty on 24/04/15.
 */
trait Ord {

  def <(that: Any): Boolean // this is abstract method! has no body!

  def <=(that: Any): Boolean = (this < that) || (this == that)

  def >(that: Any): Boolean = !(this == that)

  def >=(that: Any): Boolean = !(this < that)
}
