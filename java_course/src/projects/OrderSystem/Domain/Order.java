package Domain;

import java.util.List;
import java.util.stream.Collectors;

public record Order(
                OrderId id,
                List<CartItem> items,
                double subtotal,
                double discountAmount,
                double total,
                String placedAt) {
        public String toJson() {
                String itemJson = items.stream().map(
                                item -> """
                                                {"product": "%s","qty": %d,"subtotal": %.2f}
                                                """.formatted(item.product().name(), item.quantity(), item.subTotal()))
                                .collect(Collectors.joining(",\n ", "[\n ", "\n ]"));
                return """
                                {
                                   "id": "%s",
                                   "items":"%s",
                                   "subtotal": %.2f,
                                   "discountAmount": %.2f,
                                   "total": %.2f,
                                   "placedAt":"%s"
                                }
                                        """.formatted(id, itemJson, subtotal, discountAmount, total, placedAt);

        }
}