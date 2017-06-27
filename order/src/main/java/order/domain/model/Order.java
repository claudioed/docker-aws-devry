package order.domain.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "order")
public class Order {

  @Id
  String id;

  String customer;

  List<Item> items;

  String status;

  @Builder
  public static Order create(String customer,String status,List<Item> items){
    return new Order(UUID.randomUUID().toString(),customer,items,status);
  }

}
