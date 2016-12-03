package com.yarenty.monads


import scala.util.{Try, Success, Failure}

/**
  * Created by yarenty on 03/12/2016.
  * (C)2015 SkyCorp Ltd.
  */
object SynchonousLifeQuestion {


  def answerToLife(nb: Int): Try[Int] = {
    if (nb == 42) Success(nb)
    else Failure(new Exception("WRONG"))
  }


  def main(args: Array[String]): Unit = {

    val ans = answerToLife(42) match {
      case Success(t) => t // returns 42
      case failure@Failure(e) => failure // returns Failure(java.Lang.Exception: WRONG)
    }


    println(ans)

    // here how to check flow!
    // standard:
    //    val o1 = SomeTrait()
    //    val o2 = o1.f1()
    //    val o3 = o2.f2()
    //
    // with monad using for ...
    //    val o1 = SomeTrait()
    //    val ans = for {
    //        o2 <- o1.f1();
    //        o3 <- o2.f2()
    //    } yield o3


  }


}
