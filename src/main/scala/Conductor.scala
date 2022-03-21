package upmc.akka.ppc


import akka.actor.{Props, Actor, ActorRef, ActorSystem}

object Conductor {}

    class ConductorActor (player : ActorRef , provider : ActorRef) extends Actor{
        import DataBaseActor._
        import PlayerActor._
        device.open()

        def lancement : Int = {
            val r = new scala.util.Random
            val d1 = r.nextInt(5) + 1
            val d2 = r.nextInt(5) + 1
            d1 + d2
        }

        def receive = {
            case "StartGame" => {
                val des = lancement
                provider ! GetMeasure(des)
            }

            case Measure(l) =>{
                player !  Measure(l)
                Thread.sleep(1800)
                self ! "StartGame"
            }
        
        }
    }

