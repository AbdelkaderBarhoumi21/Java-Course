package Services;

import Domain.Order;
import Repository.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Domain.*;

class OrderService {
    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    Order placerOrder(CartService cartService) {
        if (cartService.isEmpty())
            throw new IllegalStateException("Cannot place an empty order");
        Order order = new Order(OrderId.generateId(), cartService.snapshot(), cartService.subTotal(),
                cartService.discountAmount(), cartService.total(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        orderRepository.save(order);
        cartService.clear();
        return order;
    }

}