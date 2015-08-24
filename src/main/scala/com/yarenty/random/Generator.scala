package com.yarenty.random

/**
 * Created by yarenty on 24/08/2015.
 */
trait Generator[+T] {

  self =>
  //alias for this, see below

  def generate: T


  def map[S](f: T => S): Generator[S] = new Generator[S] {
    override def generate: S = f(self.generate) // self to avoid recursive call to itself
  }


  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    override def generate: S = f(self.generate).generate
  }
}


/* lets create random tree structure */
trait Tree

case class Inner(left: Tree, right: Tree) extends Tree

case class Leaf(x: Int) extends Tree


object generators {


  /**
   * base generator - from java
   */
  val integers = new Generator[Int] {
    val rand = new java.util.Random

    def generate = rand.nextInt()
  }


  /**
   * simple boolean one
   */
  val booleans = new Generator[Boolean] {

    def generate = integers.generate > 0
  }

  /**
   * pair generator
   * @param t
   * @param u
   * @tparam T
   * @tparam U
   * @return
   */
  def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)] {
    def generate = (t.generate, u.generate)
  }

  /**
   * single value generator
   * @param x
   * @tparam T
   * @return
   */
  def single[T](x: T): Generator[T] = new Generator[T] {
    override def generate: T = x
  }

  /**
   * choose between number of options
   * @param lo
   * @param hi
   * @return
   */
  def choose (lo: Int, hi: Int): Generator[Int] = {
    for (x <- integers) yield lo + Math.abs(x) % (hi - lo)
  }

  /**
   * one of set ...
   * @param xs
   * @tparam T
   * @return
   */
  def oneOf[T](xs: T*): Generator[T] = {
    for (idx <- choose(0, xs.length)) yield xs(idx)
  }


  /**
   * list generator
   * @return
   */
  def lists: Generator[List[Int]] = for {
    isEmpty <- booleans
    list <- if (isEmpty) emptyLists else nonEmptyLists
  } yield list

  def emptyLists = single(Nil)

  def nonEmptyLists = for {
    head <- integers
    tail <- lists
  } yield head :: tail


  /**
   * Trees generator
   * @return
   */
  def leafs: Generator[Leaf] = for {
    x <- integers
  } yield Leaf(x)

  def inners: Generator[Inner] = for {
    l <- trees
    r <- trees

  } yield Inner(l, r)


  def trees: Generator[Tree] = for {
    isLeaf <- booleans
    tree <- if (isLeaf) leafs else inners
  } yield tree

  def main(args: Array[String]) {

    val test = oneOf('red, 'green, 'blue).generate

    println(test)

    val list = lists.generate
    println(list)

    val tree = trees.generate
    println(tree)
  }
}