package payment.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Data
public class Order {

  String id;

  String customer;

  List<Item> items = new ArrayList<>();

  String status;

  public boolean hasThreeElements(){
    return this.items.size() == 3;
  }

  public BigDecimal amount(){
    return items.stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQty()))).reduce(BigDecimal.ZERO,
        BigDecimal::add);
  }

}
