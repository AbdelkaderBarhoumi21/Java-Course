package Domain;

public record CartItem(
        Product product,
        int quantity) {

     public CartItem {
        if (quantity < product.minQty() || quantity > product.maxQty()) {
            throw new IllegalArgumentException("Quantity % out of range [%d, %d] for  %s".formatted(quantity,
                    product.minQty(), product.maxQty(), product.name()));
        }
    }

    public double subTotal() {
        return product.price() * quantity;
    }

    public CartItem withQuantity(int newQty) {
        return new CartItem(product, newQty);
    }
}