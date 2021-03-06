package payment.domain.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import payment.domain.model.OrderStatus;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Component
public class SQSDispatcher {

  private final AmazonSQS sqs;

  private final String queue;

  private final ObjectMapper objectMapper;

  @Autowired
  public SQSDispatcher(AmazonSQS sqs, @Qualifier("paymentQueue") String queue,ObjectMapper objectMapper) {
    this.sqs = sqs;
    this.queue = queue;
    this.objectMapper = objectMapper;
  }

  @SneakyThrows
  public void dispatch(@NonNull OrderStatus orderStatus){
    this.sqs.sendMessage(this.queue,this.objectMapper.writeValueAsString(orderStatus));
  }

}
