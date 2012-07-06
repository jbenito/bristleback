package pl.bristleback.server.bristle.conf.resolver.serialization;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.bristleback.server.bristle.api.BristlebackConfig;
import pl.bristleback.server.bristle.api.SerializationResolver;
import pl.bristleback.server.bristle.api.annotations.Serialize;
import pl.bristleback.server.bristle.conf.resolver.SpringConfigurationResolver;
import pl.bristleback.server.bristle.message.sender.BristleMessage;
import pl.bristleback.server.bristle.serialization.SerializationInput;

import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Type;

/**
 * //@todo class description
 * <p/>
 * Created on: 2011-09-04 16:40:15 <br/>
 *
 * @author Wojciech Niemiec
 */
@Component
public class SerializationAnnotationResolver {
  private static Logger log = Logger.getLogger(SerializationAnnotationResolver.class.getName());

  @Inject
  @Named(SpringConfigurationResolver.CONFIG_BEAN_NAME)
  private BristlebackConfig configuration;

  @Inject
  private SerializationInputResolver inputResolver;

  public Object resolveDefaultSerialization(Type serializedObjectType) {
    return resolveSerialization(serializedObjectType, null);
  }

  public Object resolveSerialization(Type serializedObjectType, Serialize serializeAnnotation) {
    SerializationResolver serializationResolver = configuration.getSerializationEngine().getSerializationResolver();

    SerializationInput input;
    if (serializeAnnotation == null) {
      input = new SerializationInput();
    } else {
      input = inputResolver.resolveInputInformation(serializeAnnotation);
    }

    SerializationInput messageInput = inputResolver.resolveMessageInputInformation(serializedObjectType, input);
    return serializationResolver.resolveSerialization(BristleMessage.class, messageInput);
  }
}