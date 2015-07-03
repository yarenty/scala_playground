package com.yarenty.search

/**
 * I wrote a crawler that visits web pages, stores a few keywords in a database, and follows links to other web pages.
 * I noticed that my crawler was wasting a lot of time visiting the same pages over and over, so I made a hash table
 * visited where I'm storing URLs I've already visited. Now the crawler only visits a URL if it hasn't already been
 * visited.
 * Thing is, the crawler is running on my old desktop computer in my parents' basement (where I totally don't live
 * anymore), and it keeps running out of memory because visited is getting so huge.
 * How can I trim down the amount of space taken up by visited?
 *
 * Created by yarenty on 20/05/15.
 */

class CharTree {

  class Node(val data: Char, val child: Seq[Node])

  private val root: Node = null;

  def preorder(visit: Char => Unit): Unit = {
    def recur(n: Node): Unit = {
      visit(n.data)
      for (c <- n.child) recur(c)
    }
    recur(root)
  }

  def height(n: Node): Int = {
    n.child.foldLeft(-1)((h, c) => h max height(c))
  }

  def size(n: Node): Int = {
    n.child.foldLeft(0)((s, c) => s + size(c))
  }

  private def insertSubNode(n: Node, d: Char): Node = {
//    if (n.child != null) {
//      new Node(d, null) :: n.child
//    } else {
//      n.child = List(new Node(d, null))
//      n
//    }
    n
  }

  def stringToNodeCharSeq(link: String): Node = {
    var n: Node = null
    link.toCharArray.reverse.foreach(
      c => n = new Node(c, Seq(n))
    )
    n
  }

  //
  //  def merge (n:Seq[Node], n2:Seq[Node]): CharTree= {
  //    if (n.data == n2.data) merge(n.child,n2.child)
  //    this
  //  }
  //  def merge (n:Node, n2:Node): CharTree= {
  //    if (n.data == n2.data) merge(n.child,n2.child)
  //    this
  //  }

  def exist(link: String): Boolean = {

    var n = root
    link.toCharArray.foreach(
      c => {
        if (n.data == c) {
          n = n.child(1) //???
        } else {
          return false
        }
      }
    )
    true
  }

}

object CharTree {


}

class MillionGazillion {

  //step 1: remove www. if is there
  //step 2: use tree of chars
  def isThere(link: String): Boolean = {
    true
  }

}
