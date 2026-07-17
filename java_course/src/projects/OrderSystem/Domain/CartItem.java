package Domain;

record CartItem(
        Product product,
        int quantity) {

    CartItem {
        if (quantity < product.minQty() || quantity > product.maxQty()) {
            throw new IllegalArgumentException("Quantity % out of range [%d, %d] for  %s".formatted(quantity,
                    product.minQty(), product.maxQty(), product.name()));
        }
    }

    double subTotal() {
        return product.price() * quantity;
    }

    CartItem withQuantity(int newQty) {
        return new CartItem(product, newQty);
    }
}