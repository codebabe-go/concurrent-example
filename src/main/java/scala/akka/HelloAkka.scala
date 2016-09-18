package scala.akka

import akka.actor.{Actor, ActorSystem, Props}

/**
  * author: code.babe 
  * date: 2016-09-10 12:37
  */
class HelloAkka extends Actor {

    def receive = {
        case "hello" => println("nice to meet you")
        case _ => println("i cannot understand you word")
    }

}

object Main extends App {
    // 指定配置
//    var system = ActorSystem("HelloAkka")

    /**
      * name: reference to Actor's extension
      */
    var actor = ActorSystem().actorOf(Props[HelloAkka], name = "HelloAkka")
    actor ! "hello"
    actor ! "how are you"
}

