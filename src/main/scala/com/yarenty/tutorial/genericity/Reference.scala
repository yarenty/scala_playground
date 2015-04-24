package com.yarenty.tutorial.genericity

/**
 * Very simple example of generic reference...
 *
 * Created by yarenty on 24/04/15.
 */
class Reference[T] {

  /**
   * Default value:
   * - Numeric: 0
   * - Boolean: false
   * - Unit: ()
   * - object: null
   *
   */
  private var contents: T = _

  def set(value: T) {
    contents = value
  }

  def get: T = contents

}
