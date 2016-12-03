package com.yarenty.monads

import scala.concurrent._
import scala.util.{Try, Success, Failure}
import ExecutionContext.Implicits.global
/**
  * Created by yarenty on 03/12/2016.
  * (C)2015 SkyCorp Ltd.
  */
object AsynchronousLifeQuesiton {


  // The function to be run asyncronously
  val answerToLife: Future[Int] = Future {
    42
  }

  // These are various callback functions that can be defined
  answerToLife onComplete {
    case Success(result) => result
    case Failure(t) => println("An error has occured: " + t.getMessage)
  }

  answerToLife onSuccess {
    case result => result
  }

  answerToLife onFailure {
    case t => println("An error has occured: " + t.getMessage)
  }

  def main(args: Array[String]): Unit = {
    val out = answerToLife.value // only works if the future is completed

    println(out)
  }
}
