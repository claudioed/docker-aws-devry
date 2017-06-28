package order.domain.service;

import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import order.domain.model.Order;
import order.domain.model.OrderStatus;
import order.domain.repository.OrderRepository;
import order.domain.resource.model.NewOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
@Service
public class OrderService {

  private final OrderRepository orderRepository;

  private final S3Storage s3Storage;

  private final SQSDispatcher sqsDispatcher;

  @Autowired
  public OrderService(OrderRepository orderRepository, S3Storage s3Storage, SQSDispatcher sqsDispatcher) {
    this.orderRepository = orderRepository;
    this.s3Storage = s3Storage;
    this.sqsDispatcher = sqsDispatcher;
  }

  public Order newOrder(@NonNull NewOrder newOrder){
    final Order createdOrder = this.orderRepository.save(
        Order.builder().customer(newOrder.getCustomer()).status("new").items(newOrder.getItems())
            .build());
    this.sqsDispatcher.dispatch(createdOrder);
    this.s3Storage.storage(createdOrder);
    return createdOrder;
  }

  public List<Order> orders(){
    final List<Order> orders = new ArrayList<Order>();
    final Iterable<Order> orders1 = this.orderRepository.findAll();
    orders1.forEach(orders::add);
    return orders;
  }

  public Order updateStatus(@NonNull OrderStatus orderStatus){
    final Order order = this.orderRepository.findOne(orderStatus.getId());
    order.setStatus(orderStatus.getStatus());
    return this.orderRepository.save(order);
  }

  public Order byId(@NonNull String id){
    return this.orderRepository.findOne(id);
  }

}
