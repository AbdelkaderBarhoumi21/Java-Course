
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String name = "John doe";
        int len = name.length();
        char letter = name.charAt(5);
        int index = name.indexOf("k");
        int lastIndex = name.lastIndexOf("a");
        System.out.println("The index of the letter k is: " + index);
        System.out.println("The letter at index 5 is: " + letter);
        System.out.println("The length of the name is: " + len);
        System.out.println("The last index of the letter a is: " + lastIndex);

        name = name.toUpperCase();
        System.out.println("The name in uppercase is: " + name);
        name = name.toLowerCase();
        System.out.println("The name in lowercase is: " + name);
        name = name.trim();
        System.out.println("The name after trimming is: " + name);
        name = name.replace("A", "C");
        System.out.println("The name after replacing A with C is: " + name);
        System.out.println("The name is empty?: " + name.isEmpty());

        if (name.contains(" ")) {
            System.out.println("The name contains a space.");
        } else {
            System.out.println("The name does not contain a space.");
        }

        if (name.equals("john doe")) {
            System.out.println("The name is john doe.");
        } else {
            System.out.println("The name is not john doe.");
        }

        //. substring(start,end) method = methdos used to extract a part of a string
        String email = "test@gmail.com";
        String userName = email.substring(0, email.indexOf("@"));
        String domain = email.substring(email.indexOf("@") + 1, email.length());
        System.out.println("The username is: " + userName);
        System.out.println("The domain is: " + domain);

        String mail;
        String username;
        String domaine;

        System.out.println("Enter your email: ");
        mail = scanner.nextLine();
        username = mail.substring(0, mail.indexOf("@"));
        domaine = mail.substring(mail.indexOf("@") + 1, mail.length());
        System.out.println("The username is: " + username);
        System.out.println("The domain is: " + domaine);
        scanner.close();

    }
}
