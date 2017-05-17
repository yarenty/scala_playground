package com.yarenty.classes

/**
  * Created by yarenty on 17/05/2017.
  */
object Insets extends App {

  val t1 = new NonEmpty(3, Empty, Empty)
  println(t1)

  val t2 = t1 incl 4
  println(t2)

  val t3 = new NonEmpty(7,Empty,Empty)
  
  println( t2 union t3 )
  println( t3 union t2 )

}

//superclass
abstract class IntSet {
  def contains(x: Int): Boolean

  def incl(x: Int): IntSet
  
  def union(other:IntSet):IntSet
}

//subclass
object Empty extends IntSet {
  override def contains(x: Int): Boolean = false

  override def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)

  override def union(other: IntSet): IntSet = other
  
  override def toString: String = "."
}

//subclass
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  override def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def union(other: IntSet): IntSet = 
    ((left union right) union other) incl elem

  override def toString: String = "{" + left + elem + right + "}"
}
