package scala.akka.task.job

import scala.akka.task.ScalaTask
import akka.actor.{Actor, ActorSystem, Props}

/**
  * author: code.babe 
  * date: 2016-09-12 11:28
  */
class TestTask extends ScalaTask {


    override def name(): String = {
        "TestTask1"
    }
    override def run(): Unit = {
        val system = ActorSystem(name())
        val actor = system.actorOf(Props[TestActor], name = name())

        actor ! Start()
        actor ! Shutdown()
        actor ! "hello"
    }

}

// case class define for transfer parameters
case class Start()
case class Shutdown()

/**
  * define an actor
  */
class TestActor extends Actor {
    override def receive: Receive = {
        case Start() => {
            println("akka start")
        }
        case Shutdown() => {
            println("akka shutdown")
        }
        case _ => {
            println("cannot match pattern")
        }
    }
}


