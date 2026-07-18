package Domain;

public record OrderId(String value) {
    public static OrderId generateId() {
        return new OrderId("ORD-" + System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return value;
    }
}