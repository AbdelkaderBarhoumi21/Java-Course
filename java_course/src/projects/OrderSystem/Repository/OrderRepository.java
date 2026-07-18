package Repository;

import java.util.List;
import Domain.Order;

public interface OrderRepository {
    void save(Order order);

    List<Order> loadAll();
}