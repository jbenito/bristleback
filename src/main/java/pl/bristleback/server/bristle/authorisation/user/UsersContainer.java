package pl.bristleback.server.bristle.authorisation.user;

import javolution.util.FastMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Component;
import pl.bristleback.server.bristle.api.WebsocketConnector;
import pl.bristleback.server.bristle.api.action.SendCondition;
import pl.bristleback.server.bristle.api.users.IdentifiedUser;
import pl.bristleback.server.bristle.api.users.UserFactory;
import pl.bristleback.server.bristle.authorisation.conditions.AllUsersCondition;
import pl.bristleback.server.bristle.engine.base.ConnectedUser;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class holding references to all currently connected users. Manages connection between frameworks based abstraction of
 * user ({@link IdentifiedUser}) and WebSocket internal implementation ({@link WebsocketConnector}). Provides convenient
 * methods for transforming {@link IdentifiedUser} to {@link WebsocketConnector} and vice-versa.
 *
 * @author Pawel Machowski created at 01.05.12 14:04
 */
@Component
public class UsersContainer {

  @Inject
  private UserFactory userFactory;

  private Map<String, ConnectedUser> connectedUsers = new FastMap<String, ConnectedUser>().setShared(true);

  public IdentifiedUser newUser(WebsocketConnector connector) {
    IdentifiedUser newUser = userFactory.createNewUser();
    connectedUsers.put(
      connector.getConnectorId(),
      new ConnectedUser(newUser, connector)
    );
    return newUser;
  }

  public IdentifiedUser getUserByConnector(WebsocketConnector connector) {
    return connectedUsers.get(connector.getConnectorId()).getUser();
  }

  @SuppressWarnings("unchecked")
  public List<WebsocketConnector> getConnectorsByCondition(final SendCondition condition) {
    List<WebsocketConnector> applicableConnectors = new LinkedList<WebsocketConnector>();
    for (Map.Entry<String, ConnectedUser> user : connectedUsers.entrySet()) {
      if (condition.isApplicable(user.getValue().getUser())) {
        applicableConnectors.add(user.getValue().getConnector());
      }
    }
    return applicableConnectors;
  }

  public List<WebsocketConnector> getConnectorsByCondition(List<IdentifiedUser> usersSubset, final SendCondition condition) {
    List<IdentifiedUser> userList = getUsersMeetingCondition(usersSubset, condition);
    List<WebsocketConnector> applicableConnectors = new LinkedList<WebsocketConnector>();

    for (Map.Entry<String, ConnectedUser> user : connectedUsers.entrySet()) {
      for (IdentifiedUser identifiedUser : userList) {
        if (identifiedUser.getId().equals(user.getValue().getUser().getId())) {
          applicableConnectors.add(user.getValue().getConnector());
        }
      }
    }
    return applicableConnectors;
  }

  public List<WebsocketConnector> getConnectorsByUsers(List<IdentifiedUser> usersSubset) {
    return getConnectorsByCondition(usersSubset, AllUsersCondition.getInstance());
  }

  public WebsocketConnector getConnectorByUser(IdentifiedUser user) {
    return connectedUsers.get(user.getId()).getConnector();
  }


  @SuppressWarnings("unchecked")
  private List<IdentifiedUser> getUsersMeetingCondition(List<IdentifiedUser> usersSubset, final SendCondition condition) {
    return (List<IdentifiedUser>) CollectionUtils.select(usersSubset, new Predicate() {
      @Override
      public boolean evaluate(Object object) {
        return condition.isApplicable((IdentifiedUser) object);
      }
    });
  }

  public void removeUser(WebsocketConnector connector) {
    connectedUsers.remove(connector.getConnectorId());
  }
}
