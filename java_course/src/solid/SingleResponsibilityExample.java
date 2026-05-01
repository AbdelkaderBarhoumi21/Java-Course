package solid;

public class SingleResponsibilityExample {
    public static void main(String[] args) {
    }

}

// âŒ BAD â€” one class does 3 different jobs
class User {
    void auth() {
    }

    void checkout() {
    }

}

// âœ… GOOD â€” each class has ONE reason to change
// If the email logic changes â†’ you only touch Auth. Nothing else breaks.
class Auth {
    void auth() {
    }
}

class Checkout {
    void checkout() {
    }
}