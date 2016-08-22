package com.yarenty.extractors


import replutils._

/**
  * Created by yarenty on 22/08/2016.
  * (C)2015 SkyCorp Ltd.
  */
trait User {

  def name: String

}

class FreeUser(val name: String) extends User

class PremiumUser(val name: String) extends User

object FreeUser {
  def unapply(user: FreeUser): Option[String] = Some(user.name)
}

object PremiumUser {
  def unapply(user: PremiumUser): Option[String] = Some(user.name)
}

//------------------------ multiple values
trait User2 {
  def name: String

  def score: Int
}

class FreeUser2(val name: String, val score: Int, val upgradeProbability: Double)
  extends User2

class PremiumUser2(val name: String, val score: Int) extends User2


object FreeUser2 {
  def unapply(user: FreeUser2): Option[(String, Int, Double)] =
    Some((user.name, user.score, user.upgradeProbability))
}

object PremiumUser2 {
  def unapply(user: PremiumUser2): Option[(String, Int)] = Some((user.name, user.score))
}


//----------------- boolean extractor

object premiumCandidate {
  def unapply(user: FreeUser2): Boolean = user.upgradeProbability > 0.75
}


object Test {

  def main(args: Array[String]) {

    val user1 = FreeUser.unapply(new FreeUser("Zbygnief"))
    println(user1)

    replutils.printAttrValues(user1)


    val user: User = new PremiumUser("Daniel")

    val out = user match {
      case FreeUser(name) => "Hello " + name
      case PremiumUser(name) => "Welcome back, dear " + name
    }

    println(out)


    //multiple vars
    val user2: User2 = new FreeUser2("Daniel", 3000, 0.7d)
    val out2 = user2 match {
      case FreeUser2(name, _, p) =>
        if (p > 0.75) name + ", what can we do for you today?" else "Hello " + name
      case PremiumUser2(name, _) => "Welcome back, dear " + name
    }

    println(out2)


    //boolean extractor on the fly

    val user3: User2 = new FreeUser2("Daniel", 2500, 0.8d)
    val out3 = user3 match {
      case freeUser2@premiumCandidate() => "initiateSpamProgram(freeUser)"
      case _ => "sendRegularNewsletter(user)"
    }

    println(out3)


  }

}