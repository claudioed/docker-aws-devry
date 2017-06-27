package order.domain.model;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Data
public class Item {

  String name;

  Long qty;

  BigDecimal price;

}
