package Services;

import Domain.Order;
import Repository.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Domain.*;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(CartService cartService) {
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