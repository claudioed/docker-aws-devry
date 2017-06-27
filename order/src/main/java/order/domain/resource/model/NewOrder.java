package order.domain.resource.model;

import java.util.List;
import lombok.Data;
import order.domain.model.Item;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Data
public class NewOrder {

  String customer;

  List<Item> items;

}
