package payment.domain.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import payment.domain.model.OrderStatus;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Component
public class OrderUpdaterDispatcher {

  private final SQSDispatcher dispatcher;

  @Autowired
  public OrderUpdaterDispatcher(SQSDispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  public void sendUpdate(@NonNull OrderStatus status){
    this.dispatcher.dispatch(status);
  }

}
