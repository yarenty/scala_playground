package com.yarenty.trees

/**
  * Write a function to find the 2nd largest element in a binary search tree ↴ .
  Here's a sample binary tree node class:

    class BinaryTreeNode:

      def __init__(self, value):
          self.value = value
          self.left  = None
          self.right = None

      def insert_left(self, value):
          self.left = BinaryTreeNode(value)
          return self.left

      def insert_right(self, value):
          self.right = BinaryTreeNode(value)
          return self.right

  Gotchas
  Our first thought might be to do an in-order traversal of the BST ↴ and return the second-to-last item. This means looking at every node in the BST. That would take O(n)O(n) time and O(h)O(h) space, where hh is the max height of the tree (which is lgnlgn if the tree is balanced ↴ , but could be as much as nn if not).

  We can do better than O(n)O(n) time and O(h)O(h) space.

  We can do this in one walk from top to bottom of our BST. This means O(h)O(h) time (again, that's O(\lg{n})O(lgn) if the tree is balanced, O(n)O(n) otherwise).

  A clean recursive implementation will take O(h)O(h) space in the call stack ↴ , but we can bring our algorithm down to O(1)O(1) space overall.

  Breakdown
  Let's start by solving a simplified version of the problem and see if we can adapt our approach from there. How would we find the largest element in a BST?

  A reasonable guess is to say the largest element is simply the "rightmost" element.

  So maybe we can start from the root and just step down right child pointers until we can't anymore (until the right child is None). At that point the current node is the largest in the whole tree.

  Is this sufficient? We can prove it is by contradiction:

  If the largest element were not the "rightmost," then the largest element would either:

  be in some ancestor node's left subtree, or
  have a right child.
  But each of these leads to a contradiction:

  If the node is in some ancestor node's left subtree it's smaller than that ancestor node, so it's not the largest.
  If the node has a right child that child is larger than it, so it's not the largest.
  So the "rightmost" element must be the largest.

  How would we formalize getting the "rightmost" element in code?

  We can use a simple recursive approach. At each step:

  If there is a right child, that node and the subtree below it are all greater than the current node. So step down to this child and recurse.
  Else there is no right child and we're already at the "rightmost" element, so we return its value.
    def find_largest(root_node):
      if root_node.right:
          return find_largest(root_node.right)
      return root_node.value

  Okay, so we can find the largest element. How can we adapt this approach to find the second largest element?

  Our first thought might be, "it's simply the parent of the largest element!" That seems obviously true when we imagine a nicely balanced tree like this one:

    .        ( 5 )
          /     \
        (3)     (8)
       /  \     /  \
     (1)  (4) (7)  (9)
  But what if the largest element itself has a left subtree?

    .        ( 5 )
          /     \
        (3)     (8)
       /  \     /  \
     (1)  (4) (7)  (12)
                   /
                 (10)
                 /  \
               (9)  (11)
  Here the parent of our largest is 8, but the second largest is 11!

  Drat, okay so the second largest isn't necessarily the parent of the largest...back to the drawing board...

  Wait. No. The second largest is the parent of the largest if the largest does not have a left subtree. If we can handle the case where the largest does have a left subtree, we can handle all cases, and we have a solution.

  So let's try sticking with this. How do we find the second largest when the largest has a left subtree?

  It's the largest item in that left subtree! Woah, we freaking just wrote a function for finding the largest element in a tree. We could use that here!

  How would we code this up?

    def find_largest(root_node):
      if root_node is None:
          raise Exception('Tree must have at least 1 node')
      if root_node.right is not None:
          return find_largest(root_node.right)
      return root_node.value

  def find_second_largest(root_node):
      if root_node is None or \
              (root_node.left is None and root_node.right is None):
          raise Exception('Tree must have at least 2 nodes')

      # case: we're currently at largest, and
      # largest has a left subtree
      # 2nd largest is largest in said subtree
      if root_node.left and not root_node.right:
          return find_largest(root_node.left)

      # case: we're at parent of largest,
      # and largest has no left subtree
      # so 2nd largest must be current node
      if root_node.right and \
         not root_node.right.left and \
         not root_node.right.right:
          return root_node.value

      # otherwise: step right
      return find_second_largest(root_node.right)

  Okay awesome. This'll work. It'll take O(h)O(h) time (where hh is the height of the tree) and O(h)O(h) space.

  But that hh space in the call stack ↴ is avoidable. How can we get this down to constant space?

  Solution
  We start with a function for getting the largest value. The largest value is simply the "rightmost" one, so we can get it in one walk down the tree by traversing rightward until we don't have a right child anymore:

    def find_largest(root_node):
      current = root_node
      while current:
          if not current.right:
              return current.value
          current = current.right

  With this in mind, we can also find the second largest in one walk down the tree. At each step, we have a few cases:

  If we have a left subtree but not a right subtree, then the current node is the largest overall (the "rightmost") node. The second largest element must be the largest element in the left subtree. We use our get_largest() function above to find the largest in that left subtree!
  If we have a right child, but that right child node doesn't have any children, then the right child must be the largest element and our current node must be the second largest element!
  Else, we have a right subtree with more than one element, so the largest and second largest are somewhere in that subtree. So we step right.
    def find_largest(root_node):
      current = root_node
      while current:
          if not current.right:
              return current.value
          current = current.right

  def find_second_largest(root_node):
      if root_node is None or \
              (root_node.left is None and root_node.right is None):
          raise Exception('Tree must have at least 2 nodes')

      current = root_node

      while current:
          # case: current is largest and has a left subtree
          # 2nd largest is the largest in that subtree
          if current.left and not current.right:
              return find_largest(current.left)

          # case: current is parent of largest, and
          # largest has no children, so
          # current is 2nd largest
          if current.right and \
             not current.right.left and \
             not current.right.right:
              return current.value

          current = current.right

  Complexity
  We're doing one walk down our BST, which means O(h)O(h) time, where hh is the height of the tree (again, that's O(\lg{n})O(lgn) if the tree is balanced, O(n)O(n) otherwise). O(1)O(1) space.

  What We Learned
  Here we used a "simplify, solve, and adapt" strategy.

  The question asks for a function to find the second largest element in a BST, so we started off by simplifying the problem: we thought about how to find the first largest element.

  Once we had a strategy for that, we adapted that strategy to work for finding the second largest element.

  It may seem counter-intuitive to start off by solving the wrong quesiton. But starting off with a simpler version of the problem is often much faster, because it's easier to wrap our heads around right away.

  One more note about this one:

  Breaking things down into cases is another strategy that really helped us here.

  Notice how simple finding the second largest node got when we divided it into two cases:

  The largest node has a left subtree.
  The largest node does not have a left subtree.
  Whenever a problem is starting to feel complicated. Try breaking it down into cases.

  It can be really helpful to actually draw out sample inputs for those cases. This'll keep your thinking organized and also help get your interviewer on the same page as you.



  *
  *
  * Created by yarenty on 30/11/2016.
  * (C)2015 SkyCorp Ltd.
  */

class BinaryTreeNode(val value: Int) {
  var left:BinaryTreeNode = null
  var right:BinaryTreeNode = null

  def insert_left(value:Int):BinaryTreeNode = {
    left = new BinaryTreeNode(value)
    left
  }


  def insert_right(value:Int):BinaryTreeNode = {
    right = new BinaryTreeNode(value)
    right
  }

  override def toString: String = value.toString
}



object BinarySearchTree  extends App {

  println("start")


  def find_largest(root_node:BinaryTreeNode):Int =  {
    var current = root_node
    while (current!=null) {
      if (current.right == null) return current.value
      current = current.right
    }
    0
  }

  val tree = new BinaryTreeNode(5)
  val l1 = tree.insert_left(3)
  l1.insert_left(1)
  l1.insert_right(4)
  val r1 = tree.insert_right(8)
  r1.insert_left(7)
  val r2 = r1.insert_right(12)
  val r3 = r2.insert_left(10)
  r3.insert_left(9)
  r3.insert_right(11)


  val largest = find_largest(tree)


  println(largest)







  def find_second_largest(root_node:BinaryTreeNode):Int = {
    if (root_node == null ||
    (root_node.left == null &&  root_node.right == null)) {
      throw new Exception ("Tree must have at least 2 nodes ")
    }

    var current = root_node

    while (current!=null) {
      //case: current is largest and has a left subtree
      // 2 nd largest is the largest in that subtree
      if (current.left != null && current.right == null) {
        return find_largest(current.left)
      }
      //case: current is parent of largest, and#largest has no children, so#current is 2 nd largest

      if (current.right!= null &&
       current.right.left ==null &&
       current.right.right == null )

      return current.value

      current = current.right
    }
    0
  }

  val second = find_second_largest(tree)

  println(second)


}
