package com.yarenty.strings


import scala.collection.mutable.Map

/**
 * You want to build a word cloud, an infographic where the size of a word corresponds to how often it appears
 * in the body of text.
To do this, you'll need data. Write code that takes a long string and builds its word cloud data in a hash map ↴ ,
where the keys are words and the values are the number of times the words occured.

Think about capitalized words. For example, look at these sentences:

  'After beating the eggs, Dana read the next step:'
'Add milk and eggs, then add flour and sugar.'
What do we want to do with "After", "Dana", and "add"? In this example, your final hash should include one "add"
with a value of 22. Make reasonable (not necessarily perfect) decisions about cases like "After" and "Dana".

Assume the input will only contain words and standard punctuation.

Gotchas
Are you sure your code handles hyphenated words and standard punctuation?

Are you sure your code reasonably handles the same word with different capitalization?

Try this sentence:

  "We came, we saw, we conquered...then we ate Bill's (Mille-Feuille) cake."
We can do this in O(n)O(n) runtime and space.

The final hash we return should be the only data structure whose length is tied to nn.

We should only iterate through our input string once.

Breakdown
We'll have to go through the entire input string, and we're returning a hash with every unique word. In the worst
case every word is different, so our runtime and space cost will both be at least O(n)O(n).

This challenge has several parts. Let's break them down.

Splitting the words from the input string
Populating the hash with each word
Handling words that are both uppercase and lowercase in the input string
How would you start the first part?

We could use a built-in split() function to separate our words, but if we just split on spaces we'd have to iterate
over all the words before or after splitting to clean up the punctuation. And consider em dashes or elipses, which
aren't surrounded by spaces but nonetheless separate words. Instead, we'll make our own split() function, which will
let us iterate over the input string only once.

How can we check if a character in our input string is a letter?

Two good options are to build a helper function or to use regular expressions. Either will work for this problem.
We'll build our own helper function is_letter():

  # character is a variable holding a string with length 1

def is_letter(character):
    return character in 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'
Now how can we split each word? Let's assume, for now, that our helper function will return an array of words.

We'll iterate over all the characters in the input string. How can we identify when we've reached the end of a word?

We can store current_word in a variable, and append its value to the array every time we hit a space.

Here's a simple example. It doesn't work perfectly yet—you'll need to add code to handle the end of the input string,
hyphenated words, punctuation, and edge cases.

  def split_words(input_string):
    words = []
    current_word = ''
    for character in input_string:
        if character == ' ':
            words.append(current_word)
        elif is_letter(character):
            current_word += character
    return words
Now we've solved the first part of the challenge, splitting the words. The next part is populating our hash with
unique words. What do we do with each word?

If the word is in the hash, we'll increment its count. Otherwise, we'll add it to the hash with a count of 1.

  words_to_counts = {}

def add_word_to_hash(word):
    if word in words_to_counts:
        words_to_counts[word] += 1
    else:
        words_to_counts[word] = 1
Alright, last part! How should we handle words that are both uppercase and lowercase?

Consider these sentences:

  'We came, we saw, we ate cake.'
'Friends, Romans, countrymen! Let us eat cake.'
'New tourists in New York often wait in long lines for cronuts.'
Take some time to think of possible approaches. What are some other sentences you might run into. What are all
your options?

When are words that should be lowercase not?
Why not?
What are the ideal cases we'd want in our hash?

Here are a few options:

Only make a word uppercase in our hash if it is always uppercase in the original string.
Make a word uppercase in our hash if it is ever uppercase in the original string.
Make a word uppercase in our hash if it is ever uppercase in the original string in a position that is not the first
word of a sentence.
Use an API or other tool that identifies proper nouns.
Ignore case entirely and make every word lowercase.
What are the pros and cons for each one?

Pros and cons include:

Only make a word uppercase in our hash if it is always uppercase in the original string: this will have reasonable
accuracy in very long strings where words are more likely to be included multiple times, but words that only ever
occur as the first word in a sentence will always be included as uppercase.
Make a word uppercase in our hash if it is ever uppercase in the original string: this will ensure proper nouns are
always uppercase, but any words that are ever at the start of sentences will always be uppercase too.
Make a word uppercase in our hash if it is ever uppercase in the original string in a position that is not the first
word of a sentence: this addresses the problem with option (2), but proper nouns that are only ever at the start of
sentences will be made lowercase.
Use an API or other tool that identifies proper nouns: this has a lot of potential to give us a high level of accuracy,
but we'll give up control over decisions, we'll be relying on code we didn't write, and our practical runtime may
be significantly increased.
Ignore case entirely and make every word lowercase: this will give us simplicity and consistency, but we'll lose all
accuracy for words that should be uppercase.
Any of these could be considered reasonable. Importantly, none of them are perfect. They all have tradeoffs, and it
is very difficult to write a highly accurate algorithm. Consider "cliff" and "bill" in these sentences:

  'Cliff finished his cake and paid the bill.'
'Bill finished his cake at the edge of the cliff.'
You can choose whichever of the options you'd like, or another option you thought of. For this breakdown, we're going
to choose option (1).

Now, how do we update our add_word_to_hash() function to avoid duplicate words?

Think about the different possibilities:

The word is uppercase or lowercase.
The word is already in the hash or not.
A different case of the word is already in the hash or not.
Moving forward, we can either:

Check for words that are in the hash in both cases when we're done populating the hash. If we add "Vanilla" three times
and "vanilla" eight times, we'll combine them into one "vanilla" at the end with a value 1111.
Avoid ever having a word in our hash that's both uppercase and lowercase. As we add "Vanilla"s and "vanilla"s, we'd
always only ever have one version in our hash.
We'll choose the second method since it will save us a walk through our hash. How should we start?

If the word we're adding is already in the hash in its current case, let's increment its count. What if it's not in
the hash?

There are three possibilities:

A lowercase version is in the hash (in which case we know our input word is uppercase, because if it is lowercase and
already in the hash it would have passed our first check and we'd have just incremented its count)
An uppercase version is in the hash (so we know our input word is lowercase)
The word is not in the hash in any case
Let's start with the first possibility. What do we want to do?

Because we only include a word as uppercase if it is always uppercase, we simply increment the lowercase version's count.

  # current hash
{'blue': 3}

# adding
'Blue'

# code
words_to_counts[word.lower()] += 1

# new hash
{'blue': 4}
What about the second possibility?

This is a little more complicated. We need to remove the uppercase version from our hash if we encounter a lowercase
version. But we still need the uppercase version's count!

  #current hash
{'Yellow': 6}

# adding
'yellow'

# code
words_to_counts[word] = 1
words_to_counts[word] += words_to_counts[word.capitalize()]
del words_to_counts[word.capitalize()]

# new hash
{'yellow': 7}
Finally, what if the word is not in the hash at all?

Easy—we add it and give it a count of 1.

  #current hash
{'purple': 2}

# adding
'indigo'

# code
words_to_counts[word] = 1

# new hash
{'purple': 2, 'indigo': 1}
Now we have all our pieces! We can split words, add them to a hash, and track the number of times each word occurs
without having duplicate words of the same case. Can we improve our solution?

Let's look at our runtime and space cost. We iterate through every character in the input string once and then every
word in our array once. That's a runtime of O(n)O(n), which is the best we can achieve for this challenge (we have to
look at the entire input string). The space we're using includes an array for each word and a hash for every unique
word. Our worst case is that every word is different, so our space cost is also O(n)O(n), which is also the best we
can achieve for this challenge (we have to return a hash of words).

But we can still make some optimizations!

How can we make our space cost even smaller?

We're storing all our split words in a separate array. That at least doubles the memory we use! How can we eliminate
the need for that array?

Right now, we store each word in our array as we split them. Instead, let's just immediately populate each word in
our hash!

Solution
In our solution, we made four decisions:

We used a class. This allowed us to tie our functions together, calling them on instances of our class instead of
passing references.
For our method of avoiding duplicate words with different cases, we chose to make a word uppercase in our hash only
if it is always uppercase in the original string. While this is a reasonable approach, it is imperfect (consider proper
nouns that are also lowercase words, like "Bill" and "bill").
We built our own split() function instead of using a built-in one. This allowed us to pass each word to our
add_word_to_hash() function as it was split, and to split words and eliminate punctuation in one iteration.
We made our own is_letter() function instead of using regular expressions. Either approach would work for this challenge.
To split the words in the input string and populate a hash of the unique words to the number of times they occurred, we:

Split words by spaces, em dashes and elipses—making sure to include hyphens surrounded by characters. We also include
all apostrophes (which will handle contractions nicely but will break possessives into separate words).
Populate the words in our hash as they are identified, checking if the word is already in our hash in its current
case or another case.
If the input word is uppercase and there's a lowercase version in the hash, we increment the lowercase version's count.
If the input word is lowercase and there's an uppercase version in the hash, we "demote" the uppercase version by
adding the lowercase version and giving it the uppercase version's count.

  class WordCloudData:

    def __init__(self, input_string):
        self.input_string = input_string
        self.words_to_counts = {}
        self.populate_hash()

    def populate_hash(self):
        # iterates over each character in the input string, splitting
        # words and passing them to add_word_to_hash()

        current_word = ''
        for i in range(0, len(self.input_string)):

            character = self.input_string[i]

            # if we reached the end of the string we check if the last
            # character is a letter and add the last word to our hash
            if i == len(self.input_string)-1:
                if self.is_letter(character): current_word += character
                if current_word: self.add_word_to_hash(current_word)

            # if we reach a space or emdash we know we're at the end of a word
            # so we add it to our hash and reset our current word
            elif character == ' ' or character == u'\u2014':
                if current_word: self.add_word_to_hash(current_word)
                current_word = ''

            # we want to make sure we split on elipses so if we get two periods in
            # a row we add the current word to our hash and reset our current word
            elif character == '.':
                if i < len(self.input_string)-1 and self.input_string[i+1] == '.':
                    if current_word: self.add_word_to_hash(current_word)
                    current_word = ''

            # if the character is a letter or an apostrophe, we add it to our current word
            elif self.is_letter(character) or character == '\'':
                current_word += character

            # if the character is a hyphen, we want to check if it's surrounded by letters
            # if it is, we add it to our current word
            elif character == '-':
                if i > 0 and self.is_letter(self.input_string[i-1]) and \
                    self.is_letter(self.input_string[i+1]):
                    current_word += character

    def add_word_to_hash(self, word):

        # if the word is already in the hash we increment its count
        if word in self.words_to_counts:
            self.words_to_counts[word] += 1

        # if a lowercase version is in the hash, we know our input word must be uppercase
        # but we only include uppercase words if they're always uppercase
        # so we just increment the lowercase version's count
        elif word.lower() in self.words_to_counts:
            self.words_to_counts[word.lower()] += 1

        # if an uppercase version is in the hash, we know our input word must be lowercase.
        # since we only include uppercase words if they're always uppercase, we add the
        # lowercase version and give it the uppercase version's count
        elif word.capitalize() in self.words_to_counts:
            self.words_to_counts[word] = 1
            self.words_to_counts[word] += self.words_to_counts[word.capitalize()]
            del self.words_to_counts[word.capitalize()]

        # otherwise, the word is not in the hash at all, lowercase or uppercase
        # so we add it to the hash
        else:
            self.words_to_counts[word] = 1

    def is_letter(self, character):
        return character in 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'
Complexity
Runtime and memory cost are both O(n)O(n). This is the best we can do because we have to look at every character in
the input string and we have to return a hash of every unique word. We optimized to only make one pass over our input
and have only one O(n)O(n) data structure.

Bonus
We limited our input to letters, hyphenated words and punctuation. How would you expand your functionality to include
numbers, email addresses, twitter handles, etc.?
How would you add functionality to identify phrases or words that belong together but aren't hyphenated?
("Fire truck" or "Interview Cake")
How could you improve your capitalization algorithm?
How would you avoid having duplicate words that are just plural or singular possessives?

 * Created by yarenty on 10/06/15.
 */
object WordCounter {


  def addWord(word: String, map: Map[String, Int]): Map[String, Int] = {

    if (map.contains(word)) {
      val key: Int = map.getOrElse(word, 0)
      map(word) = key + 1
    } else {
      map(word) = 1
    }
    map
  }


  def main(args: Array[String]) {
    val str = "This is a simple test. We will check if all words are counted properly. And this is again cool check."

    val map = Map.empty[String, Int]

    str.split(' ').foreach(s => addWord(s.toUpperCase, map))
    println(map)

  }

}
