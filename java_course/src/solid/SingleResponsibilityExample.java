package solid;

public class single_responsibility_example {
    public static void main(String[] args) {
    }

}

// ❌ BAD — one class does 3 different jobs
class User {
    void auth() {
    }

    void checkout() {
    }

}

// ✅ GOOD — each class has ONE reason to change
// If the email logic changes → you only touch Auth. Nothing else breaks.
class Auth {
    void auth() {
    }
}

class Checkout {
    void checkout() {
    }
}