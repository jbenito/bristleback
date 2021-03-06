package pl.bristleback.server.bristle.api;

import pl.bristleback.server.bristle.api.users.IdentifiedUser;

/**
 * This interface is meant to be implemented by application creator to handle user connection and disconnection events.
 * There can be multiple connection state listeners defined, they are run by {@link pl.bristleback.server.bristle.listener.ConnectionStateListenerChain ConnectionStateListenerChain}.
 * Listener methods are parametrized with base type of {@link pl.bristleback.server.bristle.api.users.IdentifiedUser}.
 * In all cases, actual user implementation (according to {@link pl.bristleback.server.bristle.api.users.UserFactory UserFactory}
 * used in application) is passed, listeners may be parametrized with custom user implementations so they won't be forced to use casting.
 * Currently, the order of connection state listeners execution cannot be determined
 * (which is subject to change in next Bristleback version).
 * <p/>
 * Created on: 2011-11-20 14:47:18 <br/>
 *
 * @author Wojciech Niemiec
 */
public interface ConnectionStateListener<T extends IdentifiedUser> {

  /**
   * Method invoked after connection with given user is established.
   *
   * @param identifiedUser connected user.
   */
  void userConnected(T identifiedUser);

  /**
   * Method invoked <strong>after</strong> connection with given user is closed.
   * In addition, user doesn't exists in users container and cannot receive any further messages.
   *
   * @param identifiedUser disconnected user.
   */
  void userDisconnected(T identifiedUser);
}
