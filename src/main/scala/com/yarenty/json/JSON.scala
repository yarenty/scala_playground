package com.yarenty.json

/**
  * Created by yarenty on 19/06/2017.
  */
abstract class JSON

case class JSeq(elems: List[JSON]) extends JSON

case class JObj(bindings: Map[String, JSON]) extends JSON

case class JNum(num: Double) extends JSON

case class JStr(str: String) extends JSON

case class JBool(b: Boolean) extends JSON

case object JNull extends JSON


object TestJSON extends App {

  val data = JObj(Map(
    "firstName" -> JStr("John"),
    "lastName" -> JStr("Smith"),
    "address" -> JObj(Map(
      "street" -> JStr("21 2nd Street"),
      "state" -> JStr("NY"),
      "postal" -> JNum(10021)
    )),
    "phones" -> JSeq(List(
      JObj(Map(
        "type" -> JStr("home"),
        "number" -> JStr("1212 212 12")
      )),
      JObj(Map(
        "type" -> JStr("fax"),
        "number" -> JStr("2323 2342-3323")
      ))
    ))
  ))


  def show(json: JSON): String = json match {
    case JSeq(elems) => "[" + (elems map show mkString ",") + "]"
    case JObj(bindings) =>
      val ass = bindings map {
        case (k, v) => "\"" + k + "\": " + show(v)
      } 
      "{" + (ass mkString ",") + "}"
    case JNum(n) => n.toString
    case JStr(s) => '\"' + s + '\"'
    case JBool(b) => b.toString
    case JNull => "null"
  }


  println(show(data))
}

