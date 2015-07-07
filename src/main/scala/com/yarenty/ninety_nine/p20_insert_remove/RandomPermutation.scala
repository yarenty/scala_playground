package com.yarenty.ninety_nine.p20_insert_remove


import com.yarenty.ninety_nine.p22_generate.Range

/**
 * Created by yarenty on 06/07/15.
 */
object RandomPermutation {

  def main(args: Array[String]) {
    val range = Range.rangeBuild(1, 10)

    println("range length:" + range.size)

    val out = RandomExtract.extract(range.size, range)

    println(out)
    println("output length" +out.size)
  }

}
