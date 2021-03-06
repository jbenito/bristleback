package pl.bristleback.server.bristle.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation describes a single bean property validation.
 * Full name of property must be provided, for example <em>dependency1.deeperDependency2.simpleField3</em>.
 * More validation methods (like formatting, etc) will be available in next versions.
 * <p/>
 * Created on: 2011-07-26 12:29:18 <br/>
 *
 * @author Wojciech Niemiec
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Property {

  /**
   * Gets full property path.
   *
   * @return full property path.
   */
  String name();

  /**
   * If true, this property will be marked as required.
   *
   * @return true if this property is required.
   */
  boolean required() default false;

  /**
   * If true, then this property won't be serialized/deserialized.
   *
   * @return true if this property should not be serialized/deserialized
   */
  boolean skipped() default false;
}