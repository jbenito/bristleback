package pl.bristleback.server.bristle.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation marks method as client action method. Together with {@link pl.bristleback.server.bristle.api.annotations.ClientActionClass ClientActionClass},
 * client action annotation provides complete information about single client action.
 * Using client action methods,
 * server can send messages to client. Method parameters are serialized to array as the message payload.
 * Under the hood, {@link pl.bristleback.server.bristle.message.ConditionObjectSender ConditionObjectSender} is used.
 * Each parameter can be validated using {@link pl.bristleback.server.bristle.api.annotations.Bind Bind} annotation.
 * If any parameter is not meant to be serialized, simply use {@link pl.bristleback.server.bristle.api.annotations.Ignore} on that parameter.
 * Action methods must have one of the fallowing return types:
 * <ul>
 * <li>
 * {@link pl.bristleback.server.bristle.api.action.SendCondition SendCondition} implementation.
 * </li>
 * <li>
 * {@link pl.bristleback.server.bristle.api.users.IdentifiedUser IdentifiedUser} implementation.
 * </li>
 * <li>
 * List of {@link pl.bristleback.server.bristle.api.users.IdentifiedUser IdentifiedUser} implementations.
 * </li>
 * </ul>
 * List of recipients is determined using returned object.
 * <p/>
 * Created on: 2012-05-26 10:38:26 <br/>
 *
 * @author Wojciech Niemiec
 * @see pl.bristleback.server.bristle.api.annotations.ClientActionClass ClientActionClass
 */
@Target({ElementType.METHOD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ClientAction {

  /**
   * Gets custom client action name. If not defined, method name is used as client action name.
   *
   * @return custom client action name.
   */
  String value() default "";
}
