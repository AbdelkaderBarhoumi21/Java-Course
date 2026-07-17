package Domain;
record OrderId(String value) {
    static OrderId generateId() {
        return new OrderId("ORD-" + System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return value;
    }
}