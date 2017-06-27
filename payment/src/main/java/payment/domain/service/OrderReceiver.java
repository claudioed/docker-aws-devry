package payment.domain.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import payment.domain.model.Order;
import payment.domain.model.OrderStatus;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Component
public class OrderReceiver {

  private final ObjectMapper mapper;

  private final AmazonSQS sqs;

  private final String queueURL;

  private final PaymentProcessor paymentProcessor;

  private final OrderUpdaterDispatcher orderUpdaterDispatcher;

  public OrderReceiver(ObjectMapper mapper, AmazonSQS sqs, String queueURL,
      PaymentProcessor paymentProcessor,
      OrderUpdaterDispatcher orderUpdaterDispatcher) {
    this.mapper = mapper;
    this.sqs = sqs;
    this.queueURL = queueURL;
    this.paymentProcessor = paymentProcessor;
    this.orderUpdaterDispatcher = orderUpdaterDispatcher;
  }

  @Scheduled(fixedRate = 30000)
  public void receive(){
    final List<Message> newMessages = this.sqs.receiveMessage(this.queueURL).getMessages();
    newMessages.parallelStream().forEach(message -> {
      try {
        final Order order = mapper.readValue(message.getBody(), Order.class);
        final OrderStatus orderStatus = this.paymentProcessor.process(order);
        this.orderUpdaterDispatcher.sendUpdate(orderStatus);
        this.sqs.deleteMessage(this.queueURL, message.getReceiptHandle());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

}

