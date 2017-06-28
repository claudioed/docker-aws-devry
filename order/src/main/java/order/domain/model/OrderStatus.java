package order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {

  String id;

  String status;

  @Builder
  public static OrderStatus newOrder(String id,String status){
    return new OrderStatus(id,status);
  }

}
