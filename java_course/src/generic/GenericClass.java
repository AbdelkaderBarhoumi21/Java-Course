package generic;

public class generic_class {
    public static void main(String[] args) {

        // <> in the right side => "Look at the left side and infer the type for me."
        // The compiler sees Card<Integer> on the left and fills in <Integer> on the right automatically.
        // It's shorthand for new Card<Integer>(1)
        Card<String> card = new Card<>("Java Course");
        String content = card.getContent();
        System.out.println(content);

        Card<Integer> c = new Card<>(1);
        System.out.println(c.getContent());

    }

}

class Card<T> {
    private T content;

    public Card(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return content == null;
    }
}