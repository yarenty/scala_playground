package com.yarenty.lists

import scala.util.Random

/**
 *
 * Write a function for doing an in-place ↴ shuffle of an array.
The shuffle must be "uniform," meaning each item in the original array must have the same probability of ending up in each spot in the final array.

Assume that you have a function get_random(floor, ceiling) for getting a random integer that is >=floor and <=ceiling.

Gotchas
A common first idea is to walk through the array and swap each element with a random other element. Like so:

  def get_random(floor, ceiling):
    return random.randrange(floor, ceiling + 1)

def naive_shuffle(the_array):

    # for each index in the array
    for first_index in xrange(0,len(the_array)-1):

        # grab a random other index
        second_index = get_random(0, len(the_array)-1)

        # and swap the values
        the_array[first_index], the_array[second_index] = the_array[second_index], the_array[first_index]
However, this does not give a random distribution.

Why? We could calculate the exact probabilities of two outcomes to show they aren't the same. But the math gets a little messy. Instead, think of it this way:

Suppose our array had 33 elements: [a,b,c]. This means it'll make 33 calls to get_random(0, 2). That's 33 random choices, each with 33 possibilities. So our total number of possible sets of choices is 3*3*3=273∗3∗3=27. Each of these 2727 sets of choices is equally probable.

But how many possible outcomes do we have? If you paid attention in stats class you might know the answer is 3!3!, which is 66. Or you can just list them by hand and count:

  a,b,c
a,c,b
b,a,c
b,c,a
c,b,a
c,a,b
But our function has 2727 equally-probable sets of choices. 2727 is not evenly divisible by 66. So some of our 66 possible outcomes will be achievable with more sets of choices than others.

We can do this in a single pass. O(n)O(n) time and O(1)O(1) space.

A common mistake is to have a mostly-uniform shuffle where an item is less likely to stay where it started than it is to end up in any given slot. Each item should have the same probability of ending up in each spot, including the spot where it starts.

Breakdown
It helps to start by ignoring the "in place" requirement, then adapt the approach to work in place.

Also, the name "shuffle" can be slightly misleading—the point is to arrive at a random ordering of the items from the original array. Don't fixate too much on preconceived notions of how you would "shuffle" e.g. a deck of cards.

How might we do this by hand?

We can simply choose a random item to be the first item in the resulting array, then choose another random item (from the items remaining) to be the second item in the resulting array, etc.

Assuming these choices were in fact random, this would give us a uniform shuffle. To prove it rigorously, we can show any given item aa has the same probability (\frac{1}{n}
​n
​
​1
​​ ) of ending up in any given spot.

First, some stats review: to get the probability of an outcome, you need to multiply the probabilities of all the steps required for that outcome. Like so:

Outcome	Steps	Probability
item #1 is a	a is picked first	\frac{1}{n}
​n
​
​1
​​
item #2 is a	a not picked first, a picked second	\frac{(n-1)}{n} * \frac{1}{(n-1)} =
​n
​
​(n−1)
​​ ∗
​(n−1)
​
​1
​​ = \frac{1}{n}
​n
​
​1
​​
item #3 is a	a not picked first, a not picked second, a picked third	\frac{(n-1)}{n} * \frac{(n-2)}{(n-1)} * \frac{1}{(n-2)} =
​n
​
​(n−1)
​​ ∗
​(n−1)
​
​(n−2)
​​ ∗
​(n−2)
​
​1
​​ = \frac{1}{n}
​n
​
​1
​​
item #4 is a	a not picked first, a not picked second, a not picked third, a picked fourth	\frac{(n-1)}{n} * \frac{(n-2)}{(n-1)} * \frac{(n-3)}{(n-2)} * \frac{1}{(n-3)} =
​n
​
​(n−1)
​​ ∗
​(n−1)
​
​(n−2)
​​ ∗
​(n−2)
​
​(n−3)
​​ ∗
​(n−3)
​
​1
​​ = \frac{1}{n}
​n
​
​1
​​
So, how do we implement this in code?

If we didn't have the "in place" requirement, we could allocate a new array, then one-by one take a random item from the input array, remove it, put it in the first position in the new array, and keep going until the input array is empty (well, probably a copy of the input array—best not to destroy the input)

How can we adapt this to be in-place?

What if we make our new "random" array simply be the front of our input array?

Solution
We choose a random item to move to the first index, then we choose a random other item to move to the second index, etc. We "place" an item in an index by swapping it with the item currently at that index.

Crucially, once an item is placed at an index it can't be moved. So for the first index we choose from nn items, for the second index we choose from n-1n−1 items, etc.

  def get_random(floor, ceiling):
    return random.randrange(floor, ceiling + 1)

def shuffle(the_array):
    # if it's 1 or 0 items, just return
    if len(the_array) <= 1:
        return the_array

    last_index_in_the_array = len(the_array) - 1

    # walk through from beginning to end
    for index_we_are_choosing_for in xrange(0, len(the_array)):

        # choose a random not-yet-placed item to place there
        # (could also be the item currently in that spot)
        # must be an item AFTER the current item, because the stuff
        # before has all already been placed
        random_choice_index = get_random(index_we_are_choosing_for, last_index_in_the_array)

        # place our random choice in the spot by swapping
        the_array[index_we_are_choosing_for], the_array[random_choice_index] = the_array[random_choice_index], the_array[index_we_are_choosing_for]
This is a semi-famous algorithm known as the Fisher-Yates shuffle (sometimes called the Knuth shuffle).

Complexity
O(n)O(n) time and O(1)O(1) space.
 * Created by yarenty on 22/07/2015.
 */

//import scala.collection.mutable.Lis.List

object Shuffle {


  def get_random(floor: Int, ceiling: Int) =
    Random.nextInt(ceiling + 1 - floor) + floor

  def shuffle(the_array: Array[Int]): Array[Int] = {
    //if it 's 1 or 0 items, just return
    if (the_array.length <= 1)
      return the_array

    val last_index_in_the_array = the_array.length - 1
    // walk through from beginning to end
    //var index_we_are_choosing_for: Int = 0

    for (index_we_are_choosing_for <- 0 to the_array.length - 1) {
      //#choose a random not -yet - placed item to place there
      // #(could also be the item currently in that spot)
      // //#must be an item AFTER the current item, because the stuff
      // #before has all already been placed
      val random_choice_index = get_random(index_we_are_choosing_for, last_index_in_the_array)
      //#place our random choice in the spot by swapping
      val swap = the_array(random_choice_index)
      the_array(random_choice_index) = the_array(index_we_are_choosing_for)
      the_array(index_we_are_choosing_for) = swap

    }
    the_array
  }

  def main(args: Array[String]) {
    val a = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val b = shuffle(a)
    println(shuffle(a).mkString(","))

    println(b.mkString(","))


  }

}
