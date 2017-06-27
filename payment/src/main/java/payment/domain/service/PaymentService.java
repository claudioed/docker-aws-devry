package payment.domain.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import payment.domain.model.Payment;
import payment.domain.repository.PaymentRepository;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Component
public class PaymentService {

  private final PaymentRepository paymentRepository;

  @Autowired
  public PaymentService(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  public Payment load(@NonNull String id){
    return this.paymentRepository.payment(id);
  }

}
