package Repository;

import java.util.List;
import Domain.Order;

interface OrderRepository {
    void save(Order order);

    List<Order> loadAll();
}