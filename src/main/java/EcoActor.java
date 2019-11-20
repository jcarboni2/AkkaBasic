import akka.actor.UntypedActor;
import akka.event.LoggingAdapter;
import akka.event.Logging;
  
public class EcoActor extends UntypedActor {
  
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  
  @Override
  public void onReceive(Object msg) throws Exception {
    log.info("Received Message: " + msg);
  }
}