package pl.bristleback.server.bristle.engine.base;

import pl.bristleback.server.bristle.api.WebsocketConnector;
import pl.bristleback.server.bristle.api.users.IdentifiedUser;

/**
 * Class used to manage connection between {@link IdentifiedUser} and {@link WebsocketConnector}.
 * Represents single user connected to BristleBack server.
 *
 * Pawel Machowski
 * created at 03.05.12 16:36
 */
public class ConnectedUser {

  private IdentifiedUser user;
  private WebsocketConnector connector;

  public ConnectedUser(IdentifiedUser userObject, WebsocketConnector connector) {
    this.user = userObject;
    this.connector = connector;
  }

  public IdentifiedUser getUser() {
    return user;
  }

  public WebsocketConnector getConnector() {
    return connector;
  }
}
