package com.yarenty.trees

/**
 * Write a function to see if a binary tree ↴ is "superbalanced" (a new tree property we just made up).
A tree is "superbalanced" if the difference between the depths of any two leaf nodes is no greater than one.

Gotchas
Your first thought might be to write a recursive function, thinking, "the tree is balanced if the left subtree is
balanced and the right subtree is balanced." This kind of approach works well for some other tree problems.

But this isn't quite true. Counterexample: suppose that from the root of our tree:

The left subtree only has leaves at depths 10 and 11.
The right subtree only has leaves at depths 11 and 12.
Both subtrees are balanced, but from the root we will have leaves at 3 different depths.

We could instead have our recursive function get the list of distinct leaf depths for each subtree. That could work
fine. But let's come up with an iterative solution instead. It's often favorable to use an iterative solution instead
of a recursive one because it avoids stack overflow ↴ .

We can do this in O(n)O(n) time and O(n)O(n) space.

Breakdown
Sometimes it's good to start by rephrasing or "simplifying" the problem.

The requirement of "the difference between the depths of any two leaf nodes is no greater than 1" implies that we'll
have to compare the depths of all possible pairs of leaves. That'd be expensive—if there are nn leaves, there are n^2n
​2
​​  possible pairs of leaves.

But we can simplify this requirement to require less work. For example, we could equivalently say:

"The difference between the min leaf depth and the max leaf depth is 1 or less"
"There are at most two distinct leaf depths, and they are at most 1 apart"
If you're having trouble with a recursive approach, try using an iterative one.

To get to our leaves and measure their depths, we'll have to traverse the tree somehow. What methods do we know for
traversing a tree?

Depth-first ↴ and breadth-first ↴ are common ways to traverse a tree. Which one should we use here?

The worst-case time and space costs of both are the same—you could make a case for either.

But one characteristic of our algorithm is that it could short-circuit and return False as soon as it finds two leaves
with depths more than 1 apart. So maybe we should use a traversal that will hit leaves as quickly as possible...

Depth-first traversal will generally hit leaves before breadth-first, so let's go with that. How could we write
a depth-first walk that also keeps track of our depth?

Solution
We do a depth-first walk ↴ through our tree, keeping track of the depth as we go. When we find a leaf, we throw its
depth into a list of depths if we haven't seen that depth already.

Each time we hit a leaf with a new depth, there are two ways that our tree might now be unbalanced:

There are more than 2 different leaf depths
There are exactly 2 leaf depths and they are more than 1 apart.
Why are we doing a depth-first walk and not a breadth-first ↴ one? You could make a case for either. We chose
depth-first because it reaches leaves faster, which allows us to short-circuit earlier in some cases.

  def is_balanced(tree_root):
    depths = [] # we short-circuit as soon as we find more than 2

    # we'll treat this array as a stack that will store tuples of (node, depth)
    nodes = []
    nodes.append((tree_root, 0))

    while len(nodes):

        # pop a node and its depth from the top of our stack
        node, depth = nodes.pop()

        # case: we found a leaf
        if (not node.left) and (not node.right):

            # we only care if it's a new depth
            if depth not in depths:
                depths.append(depth)

                # two ways we might now have an unbalanced tree:
                #   1) more than 2 different leaf depths
                #   2) 2 leaf depths that are more than 1 apart
                if (len(depths) > 2) or \
                    (len(depths) == 2 and abs(depths[0] - depths[1]) > 1):
                    return False

        # case: this isn't a leaf - keep stepping down
        else:
            if node.left:
                nodes.append((node.left, depth + 1))
            if node.right:
                nodes.append((node.right, depth + 1))

    return True

Complexity
O(n)O(n) time and O(n)O(n) space. The depths list never gets bigger than 3 items so it takes O(1)O(1) space, but
the nodes stack takes O(n)O(n) space.

Since the number of items in the nodes stack will never be greater than the height of the tree, we could say we're
taking O(h) space, where hh is the height of the tree. But if our tree is just one straight line (the worst case),h=n.

 * Created by yarenty on 21/10/2015.
 */
class Superbalanced {

}
