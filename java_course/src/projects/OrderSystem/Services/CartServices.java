package Services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import Domain.*;
import Discount.*;

class CartService {
    private final Map<String, CartItem> items = new LinkedHashMap<>();
    private DiscountStrategy discount = new NoDiscount();

    // add or increase quantity
    void addProduct(Product product, int qty) {
        items.merge(product.id(), new CartItem(product, qty), (oldItem, newItem) -> {
            int newQty = oldItem.quantity() + newItem.quantity();
            return oldItem.withQuantity(newQty);
        });
    }

    void increment(String productId) {
        updateQty(productId, +1);
    }

    void decrement(String productId) {
        CartItem item = getItem(productId);
        if (item.quantity() <= item.product().minQty()) {
            System.out.printf("Minimum qty for %s is %d - remove it insetad%n", item.product().name(),
                    item.product().minQty());
            return;
        }
        updateQty(productId, -1);
    }

    void removeItem(String productId) {
        if (items.remove(productId) == null) {
            throw new NoSuchElementException("Product not in cart: " + productId);
        }
        System.out.printf("Removed %s from cart%n", productId);
    }

    void applyDiscount(DiscountStrategy strategy) {
        this.discount = strategy;
        System.out.println("Discount applied: " + strategy.describe());
    }

    void clear() {
        items.clear();
        discount = new NoDiscount();
        System.out.println("Cart cleared");
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    double subTotal() {
        return items.values().stream().mapToDouble(CartItem::subTotal).sum();
    }

    double discountAmount() {
        return discount.apply(subTotal());
    }

    double total() {
        return subTotal() - discountAmount();
    }

    List<CartItem> snapshot() {
        return List.copyOf(items.values());
    }

    void printSummary() {
        if (items.isEmpty()) {
            System.out.println("  Cart is empty.");
            return;
        }
        System.out.println("\n  ┌─────────────────────────────────────────────┐");
        System.out.println("  │                   CART                      │");
        System.out.println("  ├─────────────────────────────────────────────┤");
        items.values().forEach(i -> System.out.printf("  │  %-20s x%-3d  %8.2f €  │%n",
                i.product().name(), i.quantity(), i.subTotal()));
        System.out.println("  ├─────────────────────────────────────────────┤");
        System.out.printf("  │  Subtotal:                       %8.2f €  │%n", subTotal());
        System.out.printf("  │  Discount (%s):            %8.2f €  │%n",
                discount.describe(), discountAmount());
        System.out.println("  ├─────────────────────────────────────────────┤");
        System.out.printf("  │  TOTAL:                          %8.2f €  │%n", total());
        System.out.println("  └─────────────────────────────────────────────┘");
    }

    // Private helpers

    private void updateQty(String productId, int delta) {
        CartItem item = getItem(productId);
        int newQty = clamp(item.quantity() + delta, item.product());
        items.put(productId, item.withQuantity(newQty));
    }

    private CartItem getItem(String productId) {
        CartItem item = items.get(productId);
        if (item == null)
            throw new NoSuchElementException("Product not in cart: " + productId);

        return item;
    }

    private int clamp(int qty, Product p) {
        return Math.max(p.minQty(), Math.min(qty, p.maxQty()));
    }

}