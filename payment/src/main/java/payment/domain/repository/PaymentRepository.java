package payment.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import payment.domain.model.Payment;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Component
public class PaymentRepository {

  private final DynamoDBMapper dynamoDBMapper;

  public PaymentRepository(DynamoDBMapper dynamoDBMapper) {
    this.dynamoDBMapper = dynamoDBMapper;
  }

  public void save(@NonNull Payment payment){
    this.dynamoDBMapper.save(payment);
  }

  public Payment payment(@NonNull String id){
    return this.dynamoDBMapper.load(Payment.class,id);
  }

}
