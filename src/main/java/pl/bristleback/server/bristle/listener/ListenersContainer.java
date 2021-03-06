package pl.bristleback.server.bristle.listener;

import pl.bristleback.server.bristle.api.ConnectionStateListener;

import java.util.List;

/**
 * //@todo class description
 * <p/>
 * Created on: 2011-11-20 17:16:28 <br/>
 *
 * @author Wojciech Niemiec
 */
public class ListenersContainer {

  private List<ConnectionStateListener> connectionStateListeners;

  public ListenersContainer(List<ConnectionStateListener> connectionStateListeners) {
    this.connectionStateListeners = connectionStateListeners;
  }

  public List<ConnectionStateListener> getConnectionStateListeners() {
    return connectionStateListeners;
  }
}
