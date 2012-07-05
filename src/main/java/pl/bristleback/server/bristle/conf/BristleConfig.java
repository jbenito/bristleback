package pl.bristleback.server.bristle.conf;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.bristleback.server.bristle.api.BristlebackConfig;
import pl.bristleback.server.bristle.api.DataController;
import pl.bristleback.server.bristle.api.SerializationEngine;
import pl.bristleback.server.bristle.api.ServerEngine;
import pl.bristleback.server.bristle.integration.spring.BristleSpringIntegration;
import pl.bristleback.server.bristle.listener.ListenersContainer;

/**
 * //@todo class description
 * <p/>
 * Created on: 2011-07-03 23:25:51 <br/>
 *
 * @author Wojciech Niemiec
 */
@Component
public class BristleConfig implements BristlebackConfig {
  private static Logger log = Logger.getLogger(BristleConfig.class.getName());

  private BristleSpringIntegration springIntegration;

  private ListenersContainer listenersContainer;

  private ServerEngine serverEngine;

  private MessageConfiguration messageConfiguration;

  private DataControllers dataControllers;

  private InitialConfiguration initialConfiguration;

  private SerializationEngine serializationEngine;

  public ServerEngine getServerEngine() {
    return serverEngine;
  }

  public void setServerEngine(ServerEngine serverEngine) {
    this.serverEngine = serverEngine;
  }

  public DataController getDefaultDataController() {
    return dataControllers.getDefaultController();
  }

  public DataController getDataController(String controllerName) {
    return dataControllers.getDataController(controllerName);
  }

  public DataControllers getDataControllers() {
    return dataControllers;
  }

  public void setDataControllers(DataControllers dataControllers) {
    this.dataControllers = dataControllers;
  }

  public InitialConfiguration getInitialConfiguration() {
    return initialConfiguration;
  }

  public void setInitialConfiguration(InitialConfiguration initialConfiguration) {
    this.initialConfiguration = initialConfiguration;
  }

  public ListenersContainer getListenersContainer() {
    return listenersContainer;
  }

  public void setListenersContainer(ListenersContainer listenersContainer) {
    this.listenersContainer = listenersContainer;
  }

  public SerializationEngine getSerializationEngine() {
    return serializationEngine;
  }

  public void setSerializationEngine(SerializationEngine serializationEngine) {
    this.serializationEngine = serializationEngine;
  }

  public BristleSpringIntegration getSpringIntegration() {
    return springIntegration;
  }

  public void setSpringIntegration(BristleSpringIntegration springIntegration) {
    this.springIntegration = springIntegration;
  }

  public MessageConfiguration getMessageConfiguration() {
    return messageConfiguration;
  }

  public void setMessageConfiguration(MessageConfiguration messageConfiguration) {
    this.messageConfiguration = messageConfiguration;
  }
}
