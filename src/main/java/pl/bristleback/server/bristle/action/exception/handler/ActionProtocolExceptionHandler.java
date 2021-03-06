package pl.bristleback.server.bristle.action.exception.handler;

import org.springframework.stereotype.Component;
import pl.bristleback.server.bristle.action.ActionExecutionContext;
import pl.bristleback.server.bristle.action.ActionExecutionStage;
import pl.bristleback.server.bristle.action.response.ExceptionResponse;
import pl.bristleback.server.bristle.api.action.ActionExceptionHandler;
import pl.bristleback.server.bristle.exceptions.BrokenActionProtocolException;

/**
 * //@todo class description
 * <p/>
 * Created on: 2012-03-25 10:09:51 <br/>
 *
 * @author Wojciech Niemiec
 */
@Component
public class ActionProtocolExceptionHandler implements ActionExceptionHandler<BrokenActionProtocolException> {

  @Override
  public Object handleException(BrokenActionProtocolException e, ActionExecutionContext context) {
    return new ExceptionResponse(BrokenActionProtocolException.class.getSimpleName(), context.getStage());
  }

  @Override
  public ActionExecutionStage[] getHandledStages() {
    return new ActionExecutionStage[]{ActionExecutionStage.ACTION_EXTRACTION};
  }
}
