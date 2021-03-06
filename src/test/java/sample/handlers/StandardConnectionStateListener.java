package sample.handlers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.bristleback.server.bristle.api.ConnectionStateListener;
import pl.bristleback.server.bristle.api.users.IdentifiedUser;
import pl.bristleback.server.bristle.app.BristlebackServerInstance;

/**
 * //@todo class description
 * <p/>
 * Created on: 2011-11-20 17:55:09 <br/>
 *
 * @author Wojciech Niemiec
 */
@Component
public class StandardConnectionStateListener implements ConnectionStateListener<IdentifiedUser> {

  private static Logger log = Logger.getLogger(BristlebackServerInstance.class.getName());

  @Override
  public void userConnected(IdentifiedUser user) {
    log.info("listener says that new user was added - id: " + user.getId());
  }

  @Override
  public void userDisconnected(IdentifiedUser user) {
    log.info("listener says that the user has disconnected - id: " + user.getId());
  }
}
