package com.yarenty.strings

/**
Write a recursive function for generating all permutations of an input string. Return them as a set.
  Don't worry about time or space complexity—if we wanted efficiency we'd write an iterative version.

  Your function can have loops—it just needs to also be recursive.

  Gotchas
  Make sure you have a base case ↴ ! Otherwise your function may never terminate!

  Breakdown
  Let's break the problem into subproblems. How could we re-phrase the problem of getting all permutations for "cats"
in terms of a smaller but similar subproblem?

  Let's make our subproblem be getting all permutations for all characters except the last one. If we had all
permutations for "cat," how could we use that to generate all permutations for "cats"?

  We could put the "s" in each possible position for each possible permutation of "cat"!

  These are our permutations of "cat":

    cat
  cta
  atc
  act
  tac
  tca
  For each of them, we add "s" in each possible position. So for "cat":

    cat
      scat
      csat
      cast
      cats
  And for "cta":

    cta
      scta
      csta
      ctsa
      ctas
  And so on.

  Now that we can break the problem into subproblems, we just need a base case and we have a recursive algorithm!

  Solution
  If we're making all permutations for "cat," we take all permutations for "ca" and then put "t" in each possible
position in each of those permutations. We use this approach recursively:

    def get_permutations(string):
      # base case
      if len(string) <= 1:
          return [string]

      all_chars_except_last = string[:-1]
      last_char = string[-1]

      # recursive call: get all possible permutations for all chars except last
      permutations_of_all_chars_except_last = get_permutations(all_chars_except_last)

      # put the last char in all possible positions for each of the above permutations
      possible_positions_to_put_last_char = range(len(all_chars_except_last)+1)
      permutations = set()
      for permutation_of_all_chars_except_last in permutations_of_all_chars_except_last:
          for position in possible_positions_to_put_last_char:
              permutation = permutation_of_all_chars_except_last[:position] + last_char
                  + permutation_of_all_chars_except_last[position:]
              print permutation
              permutations.add(permutation)

      return permutations

  * Created by yarenty on 02/12/2015.
  * (C)2015 SkyCorp Ltd.
  */
class Permutations {


  def get_permutations(in: String): Set[String] = {
    //base case
    if (in.length <= 1) {
      return Set(in)
    }

    val all_chars_except_last = in.substring(0, in.length - 1)
    val last_char = in.substring(in.length - 1)

    //#recursive call: get all possible permutations for all chars except last
    val permutations_of_all_chars_except_last = get_permutations(all_chars_except_last)
    //#put the last char in all possible positions for each of the above permutations
    val possible_positions_to_put_last_char = Range(0, all_chars_except_last.length + 1)
    var permutations = Set[String]()
    for (permutation_of_all_chars_except_last <- permutations_of_all_chars_except_last) {
      for (position <- possible_positions_to_put_last_char) {
        val permutation = permutation_of_all_chars_except_last.substring(0, position) + last_char +
          permutation_of_all_chars_except_last.substring(position, permutation_of_all_chars_except_last.length)

        print(s"${permutation}\n")

        permutations += permutation
      }
    }

    return permutations
  }

}

object Permutations {

  def main(args: Array[String]) {

    val p = new Permutations

    print(p.get_permutations("cats"))
  }

}
