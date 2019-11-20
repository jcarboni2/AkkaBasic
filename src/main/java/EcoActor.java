import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedAbstractActor;
import akka.actor.SupervisorStrategy.Directive;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import scala.concurrent.duration.Duration;
import akka.event.Logging;

public class EcoActor extends UntypedAbstractActor {

  LoggingAdapter log = Logging.getLogger(getContext().system(), this);

  // Declare the actor child as EcoActor attribute
  private ActorRef childActor;

  @Override
  public void preStart() throws Exception {
    super.preStart();

    // On the actor initialization, instantiate the child actor
    childActor = getContext().actorOf(Props.create(ChildActor.class), "childOfEco");
  }

  @Override
  public void onReceive(Object msg) throws Exception {
    if (msg instanceof HelloMessage) {
      log.info("Received message: " + msg);

      // Forward the received message to the child actor
      childActor.tell(msg, getSelf());
    } else {
      // Inform to the actor system that this actor does not process the message
      unhandled(msg);
    }
  }

  @Override
  public SupervisorStrategy supervisorStrategy() {
    return new OneForOneStrategy(-1, Duration.Inf(), new Function<Throwable, Directive>() {
      public Directive apply(Throwable t) throws Exception {
        return OneForOneStrategy.resume();
      }
    });
  }
}