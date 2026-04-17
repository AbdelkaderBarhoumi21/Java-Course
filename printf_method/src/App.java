public class App {
    public static void main(String[] args) throws Exception {
        // printf() = is a method used to format output
        // %[flags][width][.precision][specifier-character]

        // ===== SPECIFIERS =====
        System.out.printf("Hello %s\n", "Bro");           // %s = String %n = new line
        System.out.printf("Number: %d\n", 123);           // %d = decimal integer
        System.out.printf("Float: %f\n", 3.14159);        // %f = floating point
        System.out.printf("Char: %c\n", 'A');             // %c = character
        System.out.printf("Boolean: %b\n", true);         // %b = boolean
        System.out.printf("Hex: %x\n", 255);              // %x = hexadecimal
        System.out.printf("Octal: %o\n", 8);              // %o = octal
        System.out.printf("Scientific: %e\n", 123456.789);// %e = scientific notation

        System.out.println("---------------------------");

        // ===== WIDTH =====
        // minimum number of characters (right-aligned by default)
        System.out.printf("Hello %10s\n", "Bro");   // "       Bro"
        System.out.printf("Number: %10d\n", 42);    // "        42"

        // ===== FLAGS =====
        // -  : left-align
        // +  : show sign (+/-)
        // 0  : pad with zeros
        // ,  : group separator (thousands)
        // (  : negative numbers in parentheses 
        System.out.printf("Hello %-10s!\n", "Bro"); // left-align: "Bro       !"
        System.out.printf("Value: %+d\n", 500);     // "+500"
        System.out.printf("Value: %+d\n", -500);    // "-500"
        System.out.printf("Number: %05d\n", 42);    // "00042"
        System.out.printf("Big: %,d\n", 1000000);   // "1,000,000"
        System.out.printf("Neg: %(d\n", -100);      // "(100)"

        System.out.println("---------------------------");

        // ===== PRECISION =====
        // number of digits after the decimal point
        System.out.printf("Pi: %.2f\n", 3.14159);     // "3.14"
        System.out.printf("Pi: %.4f\n", 3.14159);     // "3.1416"
        System.out.printf("Pi: %.0f\n", 3.14159);     // "3"

        // precision on strings = max characters
        System.out.printf("Name: %.3s\n", "Abdelkader"); // "Abd"  

        System.out.println("---------------------------");

        // ===== COMBINING flags + width + precision =====
        System.out.printf("Price: $%-10.2f|\n", 9.5);   // left-align, width 10, 2 decimals
        System.out.printf("Price: $%10.2f|\n", 9.5);    // right-align, width 10, 2 decimals
        System.out.printf("Price: $%010.2f\n", 9.5);    // pad with zeros

        System.out.println("---------------------------");

        // ===== MULTIPLE ARGUMENTS =====
        String name = "Abdelkader";
        int age = 25;
        double grade = 95.5;
        System.out.printf("Name: %s | Age: %d | Grade: %.1f%%\n", name, age, grade);
    }
}
