package functions;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Method References — a shorter form of lambda when the lambda just calls
 * an existing method. Syntax: Class::method or object::method
 *
 * The 4 types of method references:
 *
 *   1. Static method reference        → ClassName::staticMethod
 *      lambda: x -> Integer.parseInt(x)
 *      ref:    Integer::parseInt
 *
 *   2. Instance method on a specific object → object::method
 *      lambda: x -> System.out.println(x)
 *      ref:    System.out::println
 *
 *   3. Instance method on a type      → ClassName::instanceMethod
 *      lambda: s -> s.toUpperCase()
 *      ref:    String::toUpperCase
 *
 *   4. Constructor reference          → ClassName::new
 *      lambda: s -> new StringBuilder(s)
 *      ref:    StringBuilder::new
 *
 * Rule of thumb: if your lambda body is JUST a method call, you can replace
 * it with a method reference — same behavior, cleaner code.
 */
public class reference_methods {
    public static void main(String[] args) {
        List<String> names = List.of("alice", "bob", "charlie");

        // 1. Static method reference → Integer::parseInt
        // Equivalent lambda: s -> Integer.parseInt(s)
        List<Integer> numbers = List.of("1", "2", "3").stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println("Parsed numbers: " + numbers); // [1, 2, 3]

        // 2. Instance method on a specific object → System.out::println
        // Equivalent lambda: name -> System.out.println(name)
        names.forEach(System.out::println); // alice / bob / charlie

        // 3. Instance method on a type → String::toUpperCase
        // Equivalent lambda: s -> s.toUpperCase()
        List<String> upper = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase: " + upper); // [ALICE, BOB, CHARLIE]

        // 4. Constructor reference → StringBuilder::new
        // Equivalent lambda: s -> new StringBuilder(s)
        List<StringBuilder> builders = names.stream()
                .map(StringBuilder::new)
                .collect(Collectors.toList());
        System.out.println("Builders: " + builders); // [alice, bob, charlie]
    }}

    

    
    
    
    
    
    
   
    
    
    
    
    
   
    
    

    
        
            

            
            
            
                    
                    
            

            
            
            

            
            
            
                    
                    
            

            
            
            
                    
                    
            
        