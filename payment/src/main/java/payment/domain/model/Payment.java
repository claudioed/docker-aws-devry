package payment.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Data
@DynamoDBTable(tableName = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

  @DynamoDBHashKey(attributeName = "id")
  String id;

  String orderId;

  String date;

  BigDecimal amount;

  @Builder
  public static Payment create(String orderId,String date,BigDecimal amount){
    return new Payment(UUID.randomUUID().toString(),orderId,date,amount);
  }

}
