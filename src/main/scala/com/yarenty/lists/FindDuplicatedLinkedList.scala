package com.yarenty.lists

/**
 * Find a duplicate, Space Edition™ BEAST MODE
In Find a duplicate, Space Edition™, we were given an array of integers where:

the integers are in the range 1..n1..n
the array has a length of n+1n+1
These properties mean the array must have at least 1 duplicate. Our challenge was to find a duplicate number, while optimizing for space. We used a divide and conquer approach, iteratively cutting the array in half to find a duplicate integer in O(n\lg{n})O(nlgn) time and O(1)O(1) space (sort of a modified binary search).

But we can actually do better. We can find a duplicate integer in O(n)O(n) time while keeping our space cost at O(1)O(1).

This is a tricky one to derive (unless you have a strong background in graph theory), so we'll get you started:

Imagine each item in the array as a node in a linked list. In any linked list ↴ , each node has a value and a "next" pointer. In this case:

The value is the integer from the array.
The "next" pointer points to the value-eth node in the list (numbered starting from 1). For example, if our value was 3, the "next" node would be the third node.
Here’s a full example:

ARRAY
POSITION
LINKED
LIST
2
,
[
3
,
[
1
,
[
3
]
1
st
2
nd
3
rd
4
th
2
3
1
3
Notice we're using "positions" and not "indices." For this problem, we'll use the word "position" to mean something like "index," but different: indices start at 0, while positions start at 1. More rigorously: position = index + 1.

Using this, find a duplicate integer in O(n)O(n) time while keeping our space cost at O(1)O(1).

Drawing pictures will help a lot with this one. Grab some paper and pencil (or a whiteboard, if you have one).

Breakdown
Here are a few sample arrays. Try drawing them out as linked lists:

  [3, 4, 2, 3, 1, 5]
[3, 1, 2, 2]
[4, 3, 1, 1, 4]
Look for patterns. Then think about how we might use those patterns to find a duplicate number in our array.

When a value is repeated, how will that effect the structure of our linked list?

If two nodes have the same value, their next pointers will point to the same node!

So if we can find a node with two incoming next pointers, we know the position of that node is a duplicate integer in our array.

For example, if there are two 2s in our array, the node in the 2nd position will have two incoming pointers.

ARRAY
POSITION
LINKED
LIST
3
,
[
1
,
[
2
,
[
2
]
1
st
2
nd
3
rd
4
th
3
1
2
2
Alright, we’re on to something. But hold on—creating a linked list would take O(n)O(n) space, and we don’t want to change our space cost from O(1)O(1)!

No problem—turns out we can just think of the array as a linked list, and traverse it without actually creating a new data structure.

If you're stuck on figuring out how to traverse the array like a linked list, don't sweat it too much. Just use a real linked list for now, while we finish deriving the algorithm.

Ok, so we figured out that the position of a node with multiple incoming pointers must be a duplicate. If we can find a node with multiple incoming pointers in a constant number of walks through our array, we can find a duplicate value in O(n)O(n) time.

How can we find a node with multiple incoming pointers?

Let's look back at those sample arrays and their corresponding linked lists, which we drew out to look for patterns:

ARRAY
POSITION
LINKED
LIST
3
,
[
4
,
[
2
,
[
3
,
[
1
,
[
5
]
1
st
2
nd
3
rd
4
th
5
th
6
th
3
4
2
3
1
5
ARRAY
POSITION
LINKED
LIST
3
,
[
1
,
[
2
,
[
2
]
1
st
2
nd
3
rd
4
th
3
1
2
2
ARRAY
POSITION
LINKED
LIST
4
,
[
3
,
[
1
,
[
1
,
[
4
]
1
st
2
nd
3
rd
4
th
5
th
4
3
1
1
4
Are there any patterns that might help us find a node with two incoming pointers?

Here’s a pattern: the last node never has any incoming pointers. This makes sense—since the array has a length n + 1n+1 and all the values are nn or less, there can never be a pointer to the last position. If nn is 5, the length of the array is 6 but there can’t be a value 6 so no pointer will ever point to the 6th node. Since it has no incoming pointers, we should treat the last position in our array as the "head" (starting point) of our linked list.

Here's another pattern: there’s never an end to our list. No pointer ever points to None. Every node has a value in the range 1..n1..n, so every node points to another node (or to itself). Since the list goes on forever, it must have a cycle (a loop). Here are the cycles in our example lists:

ARRAY
LINKED
LIST
3
,
[
4
,
[
2
,
[
3
,
[
1
,
[
5
]
3
4
2
3
1
5
ARRAY
LINKED
LIST
3
,
[
1
,
[
2
,
[
2
]
3
1
2
2
ARRAY
LINKED
LIST
4
,
[
3
,
[
1
,
[
1
,
[
4
]
4
3
1
1
4
Can we use these cycles to find a duplicate value?

If we walk through our linked list, starting from the head, at some point we will enter our cycle. Try tracing that path on the example lists above. Notice anything special about the first node we hit when we enter the cycle?

The first node in the cycle always has at least two incoming pointers!

We're getting close to an algorithm for finding a duplicate value. How can we find the beginning of a cycle?

Again, drawing an example is helpful here:

If we were traversing this list and wanted to know if we were inside a cycle, that would be pretty easy—we could just remember our current position and keep stepping ahead to see if we get to that position again.

But our problem is a little trickier—we need to know the first node in the cycle.

What if we knew the length of the cycle?

If we knew the length of the cycle, we could use the “stick method” to start at the head of the list and find the first node. We use two pointers. One pointer starts at the head of the list:

CYCLE:
4 STEPS
Then we lay down a “stick” with the same length as the cycle, by starting the second pointer at the end. So here, for example, the second pointer is starting 4 steps ahead because the cycle has 4 steps:

4 STEPS
CYCLE:
4 STEPS
Then we move the stick along the list by advancing the two pointers at the same speed (one node at a time).

When the first pointer reaches the first node in the cycle, the second pointer will have circled around exactly once. The stick wraps around the cycle, and the two ends are in the same place: the start of the cycle.

We already know where the head of our list is (the last position in the list) so we just need the length of the cycle. How can we find the length of a cycle?

If we can get inside the cycle, we can just remember a position and count how many steps it takes to get back to that position.

How can we make sure we’ve gotten inside a cycle?

Well, there has to be a cycle in our list, and at the latest, the cycle is just the last node we hit as we traverse the list from the head:

So we can just start at the head and walk nn steps. By then we'll have to be inside a cycle.

Alright, we’ve pieced together an entire strategy to find a duplicate integer! Working backwards:

We know the position of a node with multiple incoming pointers is a duplicate in our array because the nodes that pointed to it must have the same value.
We find a node with multiple incoming pointers by finding the first node in a cycle.
We find the first node in a cycle by finding the length of the cycle and advancing two pointers: one starting at the head of the linked list, and the other starting ahead as many steps as there are steps in the cycle. The pointers will meet at the first node in the cycle.
We find the length of a cycle by remembering a position inside the cycle and counting the number of steps it takes to get back to that position.
We get inside a cycle by starting at the head and walking nn steps. We know the head of the list is at position n + 1n+1.
Can you implement this? And remember—we won't want to actually create a linked list. Here's how we can traverse our array as if it were a linked list ↴ .

To get inside a cycle (step E above), we identify nn, start at the head (the node in position n + 1n+1), and walk nn steps.

  def find_duplicate(array):

    n = len(array) - 1

    # STEP 1: GET INSIDE A CYCLE
    # start at position n+1 and walk n steps to
    # find a position guaranteed to be in a cycle
    position_in_cycle = n + 1
    for _ in xrange(n):
        position_in_cycle = array[position_in_cycle - 1]
Now we’re guaranteed to be inside a cycle. To find the cycle’s length (D), we remember the current position and step ahead until we come back to that same position, counting the number of steps.

  def find_duplicate(array):

    n = len(array) - 1

    # STEP 1: GET INSIDE A CYCLE
    # start at position n+1 and walk n steps to
    # find a position guaranteed to be in a cycle
    position_in_cycle = n + 1
    for _ in xrange(n):
        position_in_cycle = array[position_in_cycle - 1]

    # STEP 2: FIND THE LENGTH OF THE CYCLE
    # find the length of the cycle by remembering a position in the cycle
    # and counting the steps it takes to get back to that position
    remembered_position_in_cycle = position_in_cycle
    current_position_in_cycle    = array[position_in_cycle - 1] # 1 step ahead
    cycle_step_count = 1

    while current_position_in_cycle != remembered_position_in_cycle:
        current_position_in_cycle = array[current_position_in_cycle - 1]
        cycle_step_count += 1
Now we have the head and the length of the cycle. We need to find the first node in the cycle (C). We set up 2 pointers: 1 at the head, and 1 ahead as many steps as there are nodes in the cycle. These two pointers form our "stick."

  # STEP 3: FIND THE FIRST NODE OF THE CYCLE
# start two pointers
#   (1) at position n+1
#   (2) ahead of position n+1 as many steps as the cycle's length
pointer_start = n + 1
pointer_ahead = n + 1
for _ in xrange(cycle_step_count):
    pointer_ahead = array[pointer_ahead - 1]
Alright, we just need to find to the first node in the cycle (B), and return a duplicate value (A)!

 * Created by yarenty on 29/04/15.
 */
class FindDuplicatedLinkedList {

}
