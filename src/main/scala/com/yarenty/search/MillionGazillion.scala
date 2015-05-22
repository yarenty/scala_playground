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


}

object CharTree {

  def insert(link: String): CharTree = {
    link.toCharArray
    val ct = new CharTree()

    ct
  }

}

class MillionGazillion {

  //step 1: remove www. if is there
  //step 2: use tree of chars
  def isThere(link: String): Boolean = {

  }

}
