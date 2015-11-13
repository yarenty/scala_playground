package com.yarenty.lists

/**
 * You have a linked list ↴ and want to find the kkth to last node.
Write a function kth_to_last_node() that takes an integer kk and the head_node of a singly linked list, and returns
the kkth to last node in the list.

For example:

  a = Node("Angel Food")
b = Node("Bundt")
c = Node("Cheese")
d = Node("Devil's Food")
e = Node("Eccles")

a.next = b
b.next = c
c.next = d
d.next = e

kth_to_last_node(2, a)
# returns the node with value "Devil's Food" (the 2nd to last node)
Gotchas
We can do this in O(n)O(n) time.

We can do this in O(1)O(1) space. If you're recursing, you're probably taking O(n)O(n) space on the call stack ↴ !

Breakdown
It might be tempting to iterate through the list until we reach the end and then walk backwards kk nodes.

But we have a singly linked list! We can’t go backwards. What else can we do?

What if we had the length of the list?

Then we could calculate how far to walk, starting from the head, to reach the kkth to last node.

If the list has nn nodes:

n
And our target is the kkth to last node:

k
th
to last
n
k
The distance from the head to the target is n-kn−k:

k
th
to last
n
k
n - k
Well, we don't know the length of the list (nn). But can we figure it out?

Yes, we could iterate from the head to the tail and count the nodes!

Can you implement this approach in code?

  def kth_to_last_node(k, head):

    # STEP 1: get the length of the list
    # start at 1, not 0
    # else we'd fail to count the head node!
    list_length = 1
    current_node = head

    # traverse the whole list,
    # counting all the nodes
    while current_node.next:
        current_node = current_node.next
        list_length += 1

    # STEP 2: walk to the target node
    # calculate how far to go, from the head,
    # to get to the kth to last node
    how_far_to_go = list_length - k

    current_node = head
    for i in xrange(how_far_to_go):
        current_node = current_node.next

    return current_node
What are our time and space costs?

O(n)O(n) time and O(1)O(1) space, where nn is the length of the list.

More precisely, it takes nn steps to get the length of the list, and another n-kn−k steps to reach the target node.
In the worst case k=0k=0, so we have to walk all the way from head to tail again to reach the target node.
This is a total of 2n2n steps, which is O(n)O(n).

Can we do better?

Mmmmaaaaaaybe.

The fact that we walk through our whole list once just to get the length, then walk through the list again to get to
the kkth to last element sounds like a lot of work. Perhaps we can do this in just one pass?

What if we had like a "stick" that was kk nodes wide. We could start it at the beginning of the list, so that the
left side of the stick was on the head and the right side was on the kkth node.

STICK
k
th
Then we could slide the stick down the list...

STICK
k
th
until the right side hit the end. At that point, the left side of the stick would be on the kkth to last node!

STICK
k
th
to last
How can we implement this? Maybe it'll help to keep two pointers?

We can allocate two variables that'll be references to the nodes at the left and right sides of the "stick"!

  def kth_to_last_node(k, head):

    left_node  = head
    right_node = head

    # move right_node to the kth node
    for i in xrange(k-1):
        right_node = right_node.next

    # starting with left_node on the head,
    # move left_node and right_node down the list,
    # maintaining a distance of k between them,
    # until right_node hits the end of the list
    while right_node.next:
        left_node  = left_node.next
        right_node = right_node.next

    # since left_node is k nodes behind right_node,
    # left_node is now the kth to last node!
    return left_node
This'll work, but does it actually save us any time?

Solution
We can think of this two ways.

First approach: use the length of the list.

walk down the whole list, counting nodes, to get the total list_length.
subtract kk from the list_length to get the distance from the head node to the target node (the kth to last node).
walk that distance from the head to arrive at the target node.
  def kth_to_last_node(k, head):

    # STEP 1: get the length of the list
    # start at 1, not 0
    # else we'd fail to count the head node!
    list_length = 1
    current_node = head

    # traverse the whole list,
    # counting all the nodes
    while current_node.next:
        current_node = current_node.next
        list_length += 1

    # if k is greater than the length of the list, there can't
    # be a kth-to-last node, so we'll return an error!
    if k > list_length:
        raise LookupError('k is larger than the length of the linked list!')

    # STEP 2: walk to the target node
    # calculate how far to go, from the head,
    # to get to the kth to last node
    how_far_to_go = list_length - k

    current_node = head
    for i in xrange(how_far_to_go):
        current_node = current_node.next

    return current_node
Second approach: maintain a kk-wide "block" in one walk down the list.

Walk one pointer kk nodes from the head. Call it right_node.
Put another pointer at the head. Call it left_node.
Walk both pointers, at the same speed, towards the tail. This keeps a distance of kk between them.
When right_node hits the tail, left_node is on the target (since it's kk nodes from the end of the list).
  def kth_to_last_node(k, head):

    left_node  = head
    right_node = head

    # move right_node to the kth node
    for i in xrange(k-1):
        # but along the way, if a right_node doesn't have a next,
        # then k is greater than the length of the list and there
        # can't be a kth-to-last node! we'll raise an error
        if not right_node.next:
            raise LookupError('k is larger than the length of the linked list!')

        right_node = right_node.next

    # starting with left_node on the head,
    # move left_node and right_node down the list,
    # maintaining a distance of k between them,
    # until right_node hits the end of the list
    while right_node.next:
        left_node  = left_node.next
        right_node = right_node.next

    # since left_node is k nodes behind right_node,
    # left_node is now the kth to last node!
    return left_node
In either approach, make sure to check if k is greater than the length of the linked list! That's bad input, and we'll
want to raise an error.

Complexity
Both approaches use O(n)O(n) time and O(1)O(1) space.

But the second approach is fewer steps, since it gets the answer "in one pass," right? Wrong.

In the first approach, we walk one pointer from head to tail (to get the list's length), then walk another pointer from
the head node to the target node (the kkth to last node).

In the second approach, right_node also walks all the way from head to tail, and left_node also walks from the head to
the target node.

So in both cases we have two pointers taking the same steps through our list. The only difference is the order in which
the steps are taken. The number of steps is the same either way.

However, the second approach might still be slightly faster, due to some caching and other optimizations that modern
processors and memory have.

Let's focus on caching. Usually when we grab some data from memory (for example, info about a linked list node), we
also store that data in a small cache right on the processor. If we need to use that same data again soon after,
we can quickly grab it from the cache. But if we don't use that data for a while, we're likely to replace it with
other stuff we've used more recently (this is called a "least recently used" replacement policy).

Both of our algorithms access a lot of nodes in our list twice, so they could exploit this caching. But notice that
in our second algorithm there's a much shorter time between the first and second times that we access a given node
(this is sometimes called "temporal locality of reference"). Thus it seems more likely that our second algorithm will
save time by using the processor's cache! But this assumes our processor's cache uses something like a "least recently
used" replacement policy—it might use something else. Ultimately the best way to really know which algorithm is faster
is to implement both and time them on a few different inputs!

Bonus
Can we do better? What if we expect nn to be huge and kk to be pretty small. In this case our target node will be
close to the end of the list...so it seems a waste that we have to walk all the way from the beginning twice.

Can we trim down the number of steps in the "second trip"? One pointer will certainly have to travel all the way
from head to tail in the list to get the total length...but can we store some "checkpoints" as we go so that the
second pointer doesn't have to start all the way at the beginning? Can we store these "checkpoints" in constant
space? Note: this approach only saves time if we know that our target node is towards the end of the list
(in other words, nn is much larger than kk).

 *
 * Created by yarenty on 05/08/2015.
 */


class LNode[A](var value: A) {     // just remember that in same package cannot be same class name!!

  var next: LNode[A] = null

  def isEmpty = {
    value == null && next == null
  }

  def length: Int = if (isEmpty) 0 else 1 + next.length

  override def toString: String = if (isEmpty) "" else value + " " + next


}


object KthElementSL {


  def kthEnd[A](k: Int, n: LNode[A]): A = {

    if (n == null) throw new IllegalArgumentException("Empty node!")
    var kth = n
    var end = n

    for (i <- 1 to k) {
      if (end != null) end = end.next
      else throw new IndexOutOfBoundsException("Not enough elements in list")
    }

    while (end.next != null) {
      kth = kth.next
      end = end.next
    }
    kth.value
  }

  def main(args: Array[String]) {

    val a = new LNode('a)
    val b = new LNode('b)
    val c = new LNode('c)
    val d = new LNode('d)
    val e = new LNode('e)

    a.next = b
    b.next = c
    c.next = d
    d.next = e

    println(kthEnd(2, a))
    // println(kthEnd(2, null))
    // println(kthEnd(6, a))
    // println(kthEnd(3, e))

  }
}
