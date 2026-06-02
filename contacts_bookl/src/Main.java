import java.util.*;

record Contact(String name, String phone, String email) {
    Contact {
        Objects.requireNonNull(name, "Name required");
        Objects.requireNonNull(phone, "Phone required");
    }

    // Optional.empty() if x = null
    // Optional.of("alice@mail.com") if x has a value
    Optional<String> emailOpt() {
        return Optional.ofNullable(email);
    }

}

class ContactBook {
    private final Map<String, Contact> byPhone = new LinkedHashMap<>(); // preserves insertion order

    void add(Contact c) {
        if (byPhone.containsKey(c.phone())) {
            throw new IllegalArgumentException("Phone number already exists: " + c.phone());
        }
        byPhone.put(c.phone(), c);
    }

    Optional<Contact> findByPhone(String phone) {
        return Optional.ofNullable(byPhone.get(phone));
    }

    // .values return a Collection<Contact> of all the values in the map
    // .keySet return a Set<String> of all the keys in the map
    // enrtySet return a Set<Map.Entry<String, Contact>> of all the key-value pairs
    // in the map
    List<Contact> search(String query) {
        String q = query.toLowerCase();
        return byPhone.values().stream().filter(c -> c.name().toLowerCase().contains(q))
                .sorted(Comparator.comparing(Contact::name)).toList();
    }

    boolean delete(String phone) {
        return byPhone.remove(phone) != null;
    }

    List<Contact> all() {
        return List.copyOf(byPhone.values());
    }

}

public class Main {
    public static void main(String[] args) {
        var book = new ContactBook();
        book.add(new Contact("Alice", "111", "alice@mail.com"));
        book.add(new Contact("Bob", "222", null)); // no email

        book.search("al").forEach(System.out::println);

        String email = book.findByPhone("222")
                .flatMap(Contact::emailOpt)
                .orElse("(no email)");
        System.out.println("Bob's email: " + email); // (no email)

        book.delete("111");
        System.out.println("Total: " + book.all().size()); // 1
    }
}