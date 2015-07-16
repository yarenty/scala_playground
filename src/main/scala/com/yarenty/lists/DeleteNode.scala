package com.yarenty.lists

/**
 *
 * Delete a node from a singly-linked list ↴ , given only a variable pointing to that node.
The input could, for example, be the variable b below:

a = Node('A')
b = Node('B')
c = Node('C')

a.next = b
b.next = c

delete_node(b)
Gotchas
We can do this in O(1)O(1) time and space! But our answer is tricky, and it could have some side effects...

Breakdown
It might be tempting to try to traverse the list from the beginning until we encounter the node we want to delete.
But in this situation, we don't know where the head of the list is—we only have a reference to the node we want to delete.

But hold on—how do we even delete a node from a linked list in general, when we do have a reference to the first node?

We'd change the previous node's pointer to skip the node we want to delete, so it just points straight to the node
after it. So if these were our nodes before deleting a node:

VALUE
NEXT
None
1
4
0
NODE TO
DELETE
These would be our nodes after our deletion:

VALUE
NEXT
None
1
4
0
DELETED
NODE
So we need a way to skip over the current node and go straight to the next node. But we don't even have access to
the previous node!

Other than rerouting the previous node's pointer, is there another way to skip from the previous pointer's value to
the next pointer's value?

What if we modify the current node instead of deleting it?

Solution
We take the value and next from the input node's next node and copy them into the input node. Now the input node's
previous node effectively skips the input node's old value!

So for example, if this was our linked list before we called our function:

VALUE
NEXT
None
3
8
2
NODE TO
DELETE
This would be our list after we called our function:

VALUE
NEXT
None
None
3
2
2
DELETED
NODE
In some languages, like C, we'd have to manually delete the node we copied from, since we won't be using that
node anymore. Here, we'll let Python's garbage collector ↴ take care of it.

  def delete_node(node_to_delete):

    # get the input node's next node, the one we want to skip to
    next_node = node_to_delete.next

    if next_node:
        # replace the input node's value and pointer with the next
        # node's value and pointer. the previous node now effectively
        # skips over the input node
        node_to_delete.value = next_node.value
        node_to_delete.next  = next_node.next

    else:
        # eep, we're trying to delete the last node!
        raise Exception("Can't delete the last node with this method!")
But be careful—there are some potential problems with this implementation:

First, it doesn't work for deleting the last node in the list. We could change the node we're deleting to have a value
of None, but the second-to-last node's next pointer would still point to a node, even though it should be None.
This could work—we could treat this last, "deleted" node with value None as a "dead node" or a "sentinel node,"
and adjust any node traversing code to stop traversing when it hits such a node. The trade-off there is we couldn't
have non-dead nodes with values set to None. Instead we chose to throw an exception in this case.

Second, this method can cause some unexpected side-effects. For example, let's say we call:

a = Node(3)
b = Node(8)
c = Node(2)

a.next = b
b.next = c

delete_node(b)
There are two potential side-effects:

Any references to the input node have now effectively been reassigned to its next node. In our example, we "deleted"
the node assigned to the variable b, but in actuality we just gave it a new value (2) and a new next! If we had another
pointer to b somewhere else in our code and we were assuming it still had its old value (8), that could cause bugs.
If there are pointers to the input node's original next node, those pointers now point to a "dangling" node (a node
that's no longer reachable by walking down our list). In our example above, c is now dangling. If we changed c, we'd
never encounter that new value by walking down our list from the head to the tail.
Complexity
O(1)time and O(1) space.

 * Created by yarenty on 15/07/2015.
 */


class Node[A](var value: A, var next: Node[A]) {
  def isEmpty = {
    value == null && next == null
  }

  def length: Int = if (isEmpty) 0 else 1 + next.length

  override def toString: String = if (isEmpty) "" else value + " " + next


}

object Node {

  def delete[A] (n: Node[A]) = {
    val next = n.next
    if (next != null) {
      n.value = next.value
      n.next = next.next
    } else {
      throw new NoSuchElementException("last element in list")
    }
  }

}

object DeleteNode {


  def main(args: Array[String]) {

    val c = new Node(3, null)
    val b = new Node(2, c)
    val a = new Node(1, b)

    println("LIST::" + a)

    Node.delete(b)

    println("after delete LIST::" + a)

  }
}
