package prog1.exercises.set04;
import java.util.Scanner;
import rl.prog1.util.terminal.Terminal;
@SuppressWarnings("SpellCheckingInspection")
public class ExerciseSet04 {
    
    static Scanner s = new Scanner(System.in);
    
    public static void startByteOverflowDemo() {
        byte num = 0;
        int i = 0;
        while (i < 10) {
            System.out.print(num + " ");
            num+=30;
            i++;
        }
        System.out.println();
    }

    public static String ask(String q) {
        System.out.print(q + " ");
        //Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static void demonstrateArithmeticOperations(){
        int a = 0;
        int b = 0;
        try {
            a = Integer.parseInt(ask("Please provide a number for the first operand: "));
            b = Integer.parseInt(ask("Please provide a number for the second operand: "));
        }catch (Exception kacke){
            System.out.println("Please provide an integer number!");
            System.exit(1);
        }

        System.out.println(""+a+" + "+b+" = "+ (a+b));
        System.out.println(""+a+" - "+b+" = "+ (a-b));
        System.out.println(""+a+" * "+b+" = "+ (a*b));
        System.out.println(""+a+" / "+b+" = "+ (a/b));
        System.out.println(""+a+" % "+b+" = "+ (a%b));
    }

    public static void convertToBinary(){
        int a = 0;
        try {
            a = Integer.parseInt(ask("Please provide a number: "));
        }catch (Exception kacke){
            System.out.println("Please provide an integer number!");
            System.exit(1);
        }

        String out = "1";

        System.out.println("Binary representation: ");
        while(a > 1){
            int rest = a % 2;
            a /= 2;
            out += String.valueOf(rest);
        }

        System.out.println(out);

    }

    public static void convertHexToDec0() {
        String hex = ask("Please provide a hexadecimal number (e.g. 1f): ");

        if(!hex.matches("-?[0-9a-fA-F]+")){
            System.out.println("Please provide a valid hex number!");
            System.exit(1);
        }

        hex = new StringBuilder(hex.toUpperCase()).reverse().toString();

        int out = 0;

        for(int i=0; i<hex.length(); i++){
            int t = switch (hex.charAt(i)) {
                case 'A' -> 10;
                case 'B' -> 11;
                case 'C' -> 12;
                case 'D' -> 13;
                case 'E' -> 14;
                case 'F' -> 15;
                default -> Integer.parseInt(String.valueOf(hex.charAt(i)));
            };
            out += (Math.pow(16,i)*t);
        }
        System.out.println(out);
    }

    public static void main(String[] denscheißhierbrauchehkeinerUNDjadaisteinunicodezeichendrin){

        while(true){
            System.out.println((int) Terminal.readChar());
        }

    }
}
