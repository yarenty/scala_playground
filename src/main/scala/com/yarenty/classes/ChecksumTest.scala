package com.yarenty.classes


/**
 * Created by yarenty on 29/04/15.
 */
object ChecksumTest {

  def main(args: Array[String]) {
    val acc = new ChecksumAccumulator

    val b: Byte = 256.toByte
    acc.add(b)

    println(acc.checksum())
    println(ChecksumAccumulator.calculate("Jaro"))

  }
}
