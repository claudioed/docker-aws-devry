package order.domain.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import order.domain.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Component
public class PaymentReceiver {

  private final ObjectMapper mapper;

  private final AmazonSQS sqs;

  private final String paymentQueue;

  private final OrderService orderService;

  @Autowired
  public PaymentReceiver(ObjectMapper mapper, AmazonSQS sqs, @Qualifier("paymentQueue") String paymentQueue,OrderService orderService) {
    this.mapper = mapper;
    this.sqs = sqs;
    this.paymentQueue = paymentQueue;
    this.orderService = orderService;
  }

  @Scheduled(fixedRate = 30000)
  public void receive(){
    final List<Message> newMessages = this.sqs.receiveMessage(this.paymentQueue).getMessages();
    newMessages.parallelStream().forEach(message -> {
      try {
        final OrderStatus status = mapper.readValue(message.getBody(), OrderStatus.class);
        this.orderService.updateStatus(status);
        this.sqs.deleteMessage(this.paymentQueue, message.getReceiptHandle());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

}

