package scala.akka.task

/**
  * author: code.babe 
  * date: 2016-09-12 11:26
  */
abstract class ScalaTask {
    def name(): String
    def run()
}
