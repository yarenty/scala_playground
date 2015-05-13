package com.yarenty.lists

import scala.util.control.Breaks._

/**
 * I want to learn some big words so people think I'm smart.
I opened up a dictionary to a page in the middle and started flipping through, looking for words I didn't know.
I put each word I didn't know at increasing indices in a huge array I created in memory. When I reached the end
of the dictionary, I started from the beginning and did the same thing until I reached the page I started at.

Now I have an array of words that are mostly alphabetical, except they start somewhere in the middle of the alphabet,
reach the end, and then start from the beginning of the alphabet. In other words, this is an alphabetically ordered
array that has been "rotated." For example:

  words = [
    'ptolemaic',
    'retrograde',
    'supplant',
    'undulate',
    'xenoepist',
    'asymptote', # <-- rotates here!
    'babka',
    'banoffee',
    'engender',
    'karpatka',
    'othellolagkage',
    ]
Write a function for finding the index of the "rotation point," which is where I started working from the beginning
of the dictionary. This array is huge (there are lots of words I don't know) so we want to be efficient here.

Gotchas
We can get O(\lg{n})O(lgn) time.

Breakdown
The set is mostly ordered. We should exploit that fact.

What's a common algorithm that takes advantage of the fact that a set is sorted to find an item efficiently?

Binary search! ↴ We can write an adapted version of binary search for this.

In each iteration of our binary search, how do we know if the rotation point is to our left or to our right?

Try drawing out an example array!

  words = [ 'k','v','a','b','c','d','e','g','i' ]
                           ^
If our "current guess" is the middle item, which is 'c' in this case, is the rotation point to the left or to the
right? How do we know?

Notice that every item to the right of our rotation point is always alphabetically before the first item in the array.

So the rotation point is to our left if the current item is less than the first item. Else it's to our right.

Solution
This is a modified version of binary search ↴ . At each iteration, we go right if the item we're looking at is greater
than the first item and we go left if the item we're looking at is less than the first item.

We keep track of the lower and upper bounds on the rotation point, calling them floor_index and ceiling_index
(initially we called them "floor" and "ceiling," but because we didn't imply the type in the name we got confused
and created bugs). When floor_index and ceiling_index are directly next to each other, we know the floor is the last
item we added before starting from the beginning of the dictionary, and the ceiling is the first item we added after.

  def find_rotation_pt(words):

    first_word = words[0]

    floor_index = 0
    ceiling_index = len(words) - 1

    while(floor_index < ceiling_index):

        # guess a point halfway between floor and ceiling
        guess_index = floor_index + ((ceiling_index - floor_index) / 2)

        # if guess comes before first word
        if words[guess_index] > first_word:
            # go right
            floor_index = guess_index
        else:
            # go left
            ceiling_index = guess_index

        # if floor and ceiling have converged
        if floor_index + 1 == ceiling_index:
            # between floor and ceiling is where we flipped to the beginning
            # so ceiling is alphabetically first
            return ceiling_index
Complexity
O(\lg{n})O(lgn) time and O(1)O(1) space, just like binary search.

We're assuming that our word lengths are bound by some constant—if they were bounded by a non-constant ll,
each of our string comparisons would cost O(l)O(l), for a total of O(l*\lg{n})O(l∗lgn) runtime.

Bonus
This function assumes that the array is rotated. If it isn't, what index will it return? How can we fix our
function to return 0 for an unrotated array?
 *
 * Created by yarenty on 13/05/15.
 */
object FindRotatedStart {


  val words = Array(
    "ptolemaic",
    "retrograde",
    "supplant",
    "undulate",
    "xenoepist",
    "asymptote", // <-- rotates here!
    "baaa",
    "babka",
    "banoffee",
    "cheque",
    "engender",
    "karpatka",
    "othellolagkage"
  )


  def findme(floor: Int, ceiling: Int): Int = {
    if (floor + 1 == ceiling) ceiling
    else {
      val guess = floor + ((ceiling - floor) / 2)

      if (words(0).compare(words(guess)) > 0)
        findme(floor, guess)
      else
        findme(guess, ceiling)
    }
  }

  def find(): Int =
    findme(0, words.length - 1)

  def main(args: Array[String]) {

    var start = 0
    var last = words.length - 1
    var idx = last

    breakable {
      while (start < last) {
        idx = ((last - start) / 2) + start
        println(idx)
        if (words(0).compare(words(idx)) > 0) {
          println(words(0) + " > " + words(idx))
          last = idx
        } else {
          println(words(0) + " < " + words(idx))
          start = idx
        }

        if (start + 1 == last) break
      }
    }
    println(words(last))


    //and recursive solution
    println(words(find))
  }

}
