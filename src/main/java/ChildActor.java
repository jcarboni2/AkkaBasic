import akka.event.LoggingAdapter;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;

public class ChildActor extends UntypedAbstractActor {
  
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  
  public void onReceive(Object msg) throws Exception {
    if (msg instanceof HelloMessage) {
      log.info("Received message: " + msg);
     }
     else {
      unhandled(msg);
     }
   }
}