package scala.java

/**
  * author: code.babe 
  * date: 2016-09-12 10:56
  */
class Scala1 extends ScalaHelper {
    override def name(name: String): String = {
        name
    }

    override def run(): Unit = {
        println(name("Hello scala"))
    }
}

object Main extends App {
    var scala1: Scala1 = new Scala1
    scala1.run();
}