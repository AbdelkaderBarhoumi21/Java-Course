import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println(Math.PI);

        double power = Math.pow(2,5);
        double sqrt = Math.sqrt(25);
        double cbrt = Math.cbrt(27);
        double abs = Math.abs(-5);
        double round = Math.round(3.14);
        double ceil= Math.ceil(3.14);
        double floor = Math.floor(3.99);
        double max = Math.max(3.0, 5.0);
        double min = Math.min(3.0, 5.0);
        double log = Math.log(Math.E);
        double log10 = Math.log10(1000);
        double exp = Math.exp(1);
        double sin = Math.sin(Math.PI / 2);
        double cos = Math.cos(0);
        double tan = Math.tan(Math.PI / 4);
        double toDegrees = Math.toDegrees(Math.PI);
        double toRadians = Math.toRadians(180);
        double random = Math.random();
        double hypot = Math.hypot(3, 4);
        double signum = Math.signum(-7.5);

        System.out.println(power);
        System.out.println(sqrt);
        System.out.println(cbrt);
        System.out.println(abs);
        System.out.println(round);
        System.out.println(ceil);
        System.out.println(floor);
        System.out.println(max);
        System.out.println(min);
        System.out.println(log);
        System.out.println(log10);
        System.out.println(exp);
        System.out.println(sin);
        System.out.println(cos);
        System.out.println(tan);
        System.out.println(toDegrees);
        System.out.println(toRadians);
        System.out.println(random);
        System.out.println(hypot);
        System.out.println(signum);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the radius of a circle: ");
            double radius = scanner.nextDouble();

            double circumference = 2 * Math.PI * radius;
            double area = Math.PI * Math.pow(radius, 2);
            double volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);

            System.out.println("Circumference: " + circumference);
            System.out.println("Area: " + area);
            System.out.println("Volume: " + volume);

            System.out.print("Enter side A of a right triangle: ");
            double a = scanner.nextDouble();
            System.out.print("Enter side B of a right triangle: ");
            double b = scanner.nextDouble();

            double c = Math.hypot(a, b);
            System.out.println("Hypotenuse (C): " + c);

            System.out.print("Enter a number to find its square root: ");
            double num = scanner.nextDouble();
            System.out.println("Square root: " + Math.sqrt(num));

            System.out.print("Enter min value: ");
            int minVal = scanner.nextInt();
            System.out.print("Enter max value: ");
            int maxVal = scanner.nextInt();

            int randomNum = (int) (Math.random() * (maxVal - minVal + 1)) + minVal;
            System.out.println("Random number: " + randomNum);
        }
    }
}
