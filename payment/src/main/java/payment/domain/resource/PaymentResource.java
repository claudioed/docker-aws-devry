package payment.domain.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.domain.model.Payment;
import payment.domain.service.PaymentService;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@RestController
@RequestMapping("/api/payment")
public class PaymentResource {

  private final PaymentService paymentService;

  @Autowired
  public PaymentResource(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Payment> load(@PathVariable("id") String id){
    return ResponseEntity.ok(this.paymentService.load(id));
  }

}
