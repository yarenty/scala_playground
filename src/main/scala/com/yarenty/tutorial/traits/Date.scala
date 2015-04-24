package com.yarenty.tutorial.traits

/**
 * Created by yarenty on 24/04/15.
 */
class Date(y: Int, m: Int, d: Int) extends Ord {
  def year = y

  def month = m

  def day = d

  /**
   * Override to display date
   * @return
   */
  override def toString() = year + "-" + month + "-" + day


  /**
    * We need to override == as default is comparing objects not individual fields
    * @param that
    * @return
    */
  override def equals(that: Any):Boolean =
  that.isInstanceOf[Date] && {
    val o = that.asInstanceOf[Date]
    o.day == day && o.month == month && o.year == year
  }

  /**
   * This must be implemented as < is abstract!
   * @param that
   * @return
   */
  override def <(that: Any): Boolean = {
    if (!that.isInstanceOf[Date])
      sys.error("cannot compare " + that + " and my Date")

    val o = that.asInstanceOf[Date]
    (year < o.year) ||
      (year == o.year && (month < o.month ||
        (month == o.month && day < o.day)))

  }
}
