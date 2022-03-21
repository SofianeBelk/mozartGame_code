package upmc.akka.ppc

import akka.actor.{Props,  Actor,  ActorRef,  ActorSystem}

object Concert extends App {
  import DataBaseActor._
  import PlayerActor._

  val system = ActorSystem("MozatGame")

  val database =  system.actorOf(Props(new DataBaseActor()), "DataBaseActor")
  val player =    system.actorOf(Props(new PlayerActor(database)), "Player")
  //database ! GetMeasure(0)
  val conductor : ActorRef  = system.actorOf(Props(new ConductorActor(player,system.actorOf(Props(new ProviderActor(database,conductor)), "provider"))), "Conductor") 

  conductor ! "StartGame" 
  println("starting Mozart's game")
 }
