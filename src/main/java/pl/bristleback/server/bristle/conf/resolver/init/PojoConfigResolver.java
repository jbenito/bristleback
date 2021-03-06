package pl.bristleback.server.bristle.conf.resolver.init;

import org.apache.log4j.Level;
import org.springframework.util.Assert;
import pl.bristleback.server.bristle.api.InitialConfigurationResolver;
import pl.bristleback.server.bristle.conf.EngineConfig;
import pl.bristleback.server.bristle.conf.InitialConfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * This configuration resolver simply contains setter methods for each configuration setting.
 * Default values are taken from {@link pl.bristleback.server.bristle.conf.resolver.init.DefaultConfigurationResolver DefaultConfigurationResolver} instance.
 * <p/>
 * Created on: 2012-01-31 17:16:26 <br/>
 *
 * @author Wojciech Niemiec
 */
public class PojoConfigResolver implements InitialConfigurationResolver {

  private InitialConfiguration initialConfiguration;

  /**
   * Creates an instance of initial configuration with filled default configuration values.
   */
  public PojoConfigResolver() {
    InitialConfigurationResolver defaultConfigurationResolver = new DefaultConfigurationResolver();
    initialConfiguration = defaultConfigurationResolver.resolveConfiguration();
  }

  @Override
  public InitialConfiguration resolveConfiguration() {
    return initialConfiguration;
  }

  /**
   * Sets acceptable controller names. Each client chooses one data controller from list of available controllers.
   * Data controllers must implement {@link pl.bristleback.server.bristle.api.DataController} interface.
   *
   * @param acceptedControllerNames names of controllers to activate.
   */
  public void setAcceptedControllerNames(String... acceptedControllerNames) {
    assertThatArrayIsNotEmpty(acceptedControllerNames);
    initialConfiguration.setAcceptedControllerNames(new HashSet<String>(Arrays.asList(acceptedControllerNames)));
    initialConfiguration.setDefaultControllerName(acceptedControllerNames[0]);
  }

  /**
   * Sets logging level used in Bristleback application. Values should be taken from {@link org.apache.log4j.Level} fields.
   *
   * @param loggingLevel logging level used in Bristleback application.
   */
  public void setLoggingLevel(String loggingLevel) {
    initialConfiguration.setLoggingLevel(Level.toLevel(loggingLevel));
  }

  /**
   * Sets engine used in all serialization and deserialization operations. Serialization engine must implement
   * {@link pl.bristleback.server.bristle.api.SerializationEngine} interface.
   *
   * @param serializationEngine serialization engine implementation.
   */
  public void setSerializationEngine(String serializationEngine) {
    initialConfiguration.setSerializationEngine(serializationEngine);
  }

  /**
   * Sets server engine. Server engine must implement {@link pl.bristleback.server.bristle.api.ServerEngine} interface.
   *
   * @param engineName server engine.
   */
  public void setEngineName(String engineName) {
    EngineConfig engineConfig = initialConfiguration.getEngineConfiguration();
    engineConfig.setName(engineName);
  }

  /**
   * Sets port on which server engine will listen for new connections.
   * In {@link pl.bristleback.server.bristle.api.ServletServerEngine ServletServerEngine} implementations,
   * engine port may be determined by the web server used.
   *
   * @param enginePort engine port.
   */
  public void setEnginePort(int enginePort) {
    EngineConfig engineConfig = initialConfiguration.getEngineConfiguration();
    engineConfig.setPort(enginePort);
  }

  /**
   * Sets maximum connection idle time (in milliseconds), after which the connection will be closed.
   *
   * @param timeout maximum connection idle time.
   */
  public void setEngineTimeout(int timeout) {
    EngineConfig engineConfig = initialConfiguration.getEngineConfiguration();
    engineConfig.setTimeout(timeout);
  }

  /**
   * Sets maximum message size.
   *
   * @param maxMessageSize maximum message size.
   */
  public void setEngineMaxMessageSize(int maxMessageSize) {
    EngineConfig engineConfig = initialConfiguration.getEngineConfiguration();
    engineConfig.setMaxFrameSize(maxMessageSize);
  }

  /**
   * Sets additional engine properties.
   *
   * @param properties additional engine properties.
   */
  public void setEngineProperties(Map<String, String> properties) {
    EngineConfig engineConfig = initialConfiguration.getEngineConfiguration();
    engineConfig.setProperties(properties);
  }

  /**
   * This property is currently not used.
   *
   * @param rejectedDomains rejected domain names.
   */
  public void setEngineRejectedDomains(List<String> rejectedDomains) {
    EngineConfig engineConfig = initialConfiguration.getEngineConfiguration();
    engineConfig.setRejectedDomains(rejectedDomains);
  }

  /**
   * Sets maximum server engine buffer size for each connector.
   *
   * @param bufferSize maximum buffer size.
   */
  public void setMaxBufferSize(int bufferSize) {
    EngineConfig engineConfig = initialConfiguration.getEngineConfiguration();
    engineConfig.setMaxBufferSize(bufferSize);
  }

  /**
   * Sets the name of user factory bean that will be used to resolve {@link pl.bristleback.server.bristle.api.users.IdentifiedUser IdentifiedUser}
   * implementations on connection start. User factory must implement {@link pl.bristleback.server.bristle.api.users.UserFactory} interface.
   *
   * @param userFactory user factory.
   */
  public void setUserFactory(String userFactory) {
    initialConfiguration.setUserFactory(userFactory);
  }

  private void assertThatArrayIsNotEmpty(String... parameters) {
    Assert.notEmpty(parameters, "Exception while resolving initial configuration. \n"
      + "Empty array is not allowed as the configuration parameter value.");
  }
}
