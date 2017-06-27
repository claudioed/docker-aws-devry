package order.domain.resource;

import java.util.List;
import order.domain.model.Order;
import order.domain.resource.model.NewOrder;
import order.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@RestController
@RequestMapping("/api/order")
public class OrderResource {

  private final OrderService orderService;

  @Autowired
  public OrderResource(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<Order> newOrder(NewOrder newOrder){
    return ResponseEntity.status(201).body(this.orderService.newOrder(newOrder));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Order> byId(@PathVariable("id") String id){
    return ResponseEntity.status(200).body(this.orderService.byId(id));
  }

  @GetMapping
  public ResponseEntity<List<Order>> loadAll(){
    return ResponseEntity.status(200).body(this.orderService.orders());
  }

}
