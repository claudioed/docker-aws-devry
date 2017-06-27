package payment.domain.service;

import java.time.LocalDateTime;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.domain.model.Order;
import payment.domain.model.OrderStatus;
import payment.domain.model.Payment;
import payment.domain.repository.PaymentRepository;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Service
public class PaymentProcessor {

  private final PaymentRepository paymentRepository;

  private final S3Storage s3Storage;

  @Autowired
  public PaymentProcessor(PaymentRepository paymentRepository, S3Storage s3Storage) {
    this.paymentRepository = paymentRepository;
    this.s3Storage = s3Storage;
  }

  public OrderStatus process(@NonNull Order order){
    if(order.hasThreeElements()){
      return OrderStatus.builder().id(order.getId()).status("declined").build();
    } else{
      final Payment payment = Payment.builder().amount(order.amount()).orderId(order.getId())
          .date(LocalDateTime.now().toString()).build();
      this.paymentRepository.save(payment);
      this.s3Storage.storage(payment);
      return OrderStatus.builder().id(order.getId()).status("approved").build();
    }

  }

}
