package order.domain.repository;

import order.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * @author claudioed on 26/06/17. Project docker-aws-devry
 */
public interface OrderRepository extends CrudRepository<Order,String>{}
