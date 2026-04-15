
import java.util.Scanner;

public class App {
    public static void main(String[] args)  {
        //MAD LIBS games 

        Scanner scanner = new Scanner(System.in);

        String adjective1;
        String noun1;
        String adjective2;
        String verb1;
        String adjective3;

        System.out.print("Enter an adjective: ");
        adjective1 =scanner.nextLine();
        System.out.print("Enter a noun: ");
        noun1 =scanner.nextLine();
        System.out.print("Enter another adjective: ");
        adjective2 =scanner.nextLine();
        System.out.print("Enter a verb: ");
        verb1 =scanner.nextLine();
        System.out.print("Enter one more adjective: ");
        adjective3 =scanner.nextLine();


        System.out.println("Today i want to a " + adjective1 + " Zoo.");
        System.out.println("In an exhibit, I saw a "+ noun1 + ".");
        System.out.println(noun1 + " was "+ adjective2 + " and " + verb1+ "!");
        System.out.println("I was "+ adjective3 +"!" );

        scanner.close();
    }
}
