public class App {
    public static void main(String[] args) throws Exception {
        String name = "John doe";
        int len =name.length();
        char letter =name.charAt(5);
        int index = name.indexOf("k");
        int lastIndex=name.lastIndexOf("a");
        System.out.println("The index of the letter k is: " + index);
        System.out.println("The letter at index 5 is: " + letter);
        System.out.println("The length of the name is: " + len);
        System.out.println("The last index of the letter a is: " + lastIndex);

        name=name.toUpperCase();
        System.out.println("The name in uppercase is: " + name);
        name=name.toLowerCase();
        System.out.println("The name in lowercase is: " + name);
        name = name.trim();
        System.out.println("The name after trimming is: " + name);
        name =name.replace("A", "C");
        System.out.println("The name after replacing A with C is: " + name);
        System.out.println("The name is empty?: " + name.isEmpty());


        if(name.contains(" ")){
            System.out.println("The name contains a space.");
        }else{
            System.out.println("The name does not contain a space.");
        }

        if(name.equals("john doe")){
            System.out.println("The name is john doe.");
        }else{
            System.out.println("The name is not john doe.");
        }
    }
}
