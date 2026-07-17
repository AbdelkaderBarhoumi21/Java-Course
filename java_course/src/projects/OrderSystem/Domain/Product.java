package Domain;

record Product(
        String id,
        String name,
        double price,
        int minQty,
        int maxQty) {
    Product {
        if (minQty < 1)
            throw new IllegalArgumentException("minQty must be >=1");
        if (maxQty < minQty)
            throw new IllegalArgumentException("maxQty must be >= minQty");
        if (price < 0)
            throw new IllegalArgumentException("Price must be >=0");
    }

}