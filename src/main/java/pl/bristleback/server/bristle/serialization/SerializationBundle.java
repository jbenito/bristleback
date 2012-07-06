package pl.bristleback.server.bristle.serialization;

import org.apache.log4j.Logger;
import pl.bristleback.server.bristle.exceptions.SerializationResolvingException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * //@todo class description
 * <p/>
 * Created on: 2011-10-08 14:46:06 <br/>
 *
 * @author Wojciech Niemiec
 */
public class SerializationBundle {
  private static Logger log = Logger.getLogger(SerializationBundle.class.getName());

  private Field field;

  private Object defaultSerialization;
  private Map<String, Object> serializationMap = new HashMap<String, Object>();

  public SerializationBundle(Field field) {
    this.field = field;
  }

  public void addDefaultSerialization(Object serialization) {
    if (defaultSerialization != null) {
      throw new SerializationResolvingException("Multiple default serialization operation on field" + field);
    }
    this.defaultSerialization = serialization;
  }

  public void addSerialization(String path, Object serialization) {
    if (serializationMap.containsKey(path)) {
      throw new SerializationResolvingException("Multiple serialization operation within the same path on field" + field);
    }
    serializationMap.put(path, serialization);
  }

  public Object getDefaultSerialization() {
    return defaultSerialization;
  }

  public Object getSerialization(String propertyPath) {
    return serializationMap.get(propertyPath);
  }

  public Map<String, Object> getSerializationMap() {
    return serializationMap;
  }

  public Field getField() {
    return field;
  }

  public boolean containsDefaultSerialization() {
    return defaultSerialization != null;
  }
}