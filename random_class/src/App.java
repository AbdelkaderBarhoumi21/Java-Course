
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        
        int number= random.nextInt(100); // 0 to 99
        int number2= random.nextInt(1,10); // 1 to 9 (exclusive of 10)
        boolean boolValue = random.nextBoolean();

        if(boolValue) {
            System.out.println("The boolean value is true.");
        } else {
            System.out.println("The boolean value is false.");
        }
    
        System.out.println("Generated random number: " + number);
        System.out.println("Generated random number: " + number2);
     
    }
}
