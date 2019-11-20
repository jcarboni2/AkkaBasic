import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.Props;
  
public class Start {
  public static void main(String[] args) {
  
    //Create an Actor System, which is the Akka container.
    ActorSystem system = ActorSystem.create("HelloSystem");
  
    // Creating the actor EcoActor
    ActorRef ecoActor = system.actorOf(Props.create(EcoActor.class), "eco");
     
    // Sending the message to the actor
    ecoActor.tell(new HelloMessage(), ActorRef.noSender());
  }
}