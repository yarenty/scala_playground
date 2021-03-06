package com.yarenty.files

import java.io.File

/**
 * Created by yarenty on 05/05/15.
 */
object FileMatcher extends App {

  private def filesHere = (new File(".")).listFiles()

  def filesEnding(query: String) =
    for (file <- filesHere; if file.getName.endsWith(query)) yield file

  def filesContaining(query: String) =
    for (file <- filesHere; if file.getName.contains(query)) yield file

  def filesRegex(query: String) =
    for (file <- filesHere; if file.getName.matches(query)) yield file


  //
  // and replacement of all above...
  def filesMatching(query: String, matcher: (String, String) => Boolean) =
    for (file <- filesHere; if matcher(file.getName, query)) yield file

  def filesEnding2(query: String) = filesMatching(query, _.endsWith(_))

  def filesContaining2(query: String) = filesMatching(query, _.contains(_))

  def filesRegex2(query: String) = filesMatching(query, _.matches(_))


  //
  //and even more clean

  def filesMatching3(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName)) yield file

  def filesEnding3(query: String) = filesMatching3(_.endsWith(query))

  def filesContaining3(query: String) = filesMatching3(_.contains(query))

  def filesRegex3(query: String) = filesMatching3(_.matches(query))

  filesEnding("md").foreach(println)

  filesMatching("md", _.endsWith(_)).foreach(println)
  filesEnding3("md").foreach(println)

}
