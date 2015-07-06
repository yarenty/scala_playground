package com.yarenty.ninety_nine.p20_insert_remove

import com.yarenty.ninety_nine.p22_generate.Range

/**
 * Created by yarenty on 06/07/15.
 */
object Lotto {

  def main(args: Array[String]) {
    val range = Range.rangeBuild(1, 42)
    println(RandomExtract.extract(6, range))
  }

}
