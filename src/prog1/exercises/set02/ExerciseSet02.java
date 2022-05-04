package prog1.exercises.set02;
import java.util.Scanner;

public class ExerciseSet02 {
    static Scanner s = new Scanner(System.in);
    public static int computeSum (int number)
    {
        int result = 0;
        int i = 1;
        while (i <= number)
        {
            result = result + i;
            i = i + 1;
        }
        return result;
    }
    public static void main (String[] args)
    {
        System.out.print("\nProvide a number:");
        //Scanner s = new Scanner(System.in);
        int parameter = 0;
        try {
            parameter = Integer.parseInt(s.nextLine());
        } catch (Exception merde) {
            System.out.println("Please provide an integer number!");
            System.exit(500);
        }
        if (parameter > 100)
        {
            System.out.println("This would be ugly with such a high number like: " + parameter);
        }
        else {
            System.out.println("ComputeSum: " + computeSum(parameter));
            printSequence1(parameter);
            printSequence2(parameter);
            printSequence3(parameter);
            printTriangle(parameter);
        }
    }
    //Aufgabe 3a
    public static void printSequence1 (int number)
    {
        System.out.print("Sequence1: ");
        for(int i = 0; i < number; i++)
        {
            System.out.print(" " + i*i);
        }
        System.out.println(" ");
    }
    //Aufgabe 3b
    public static void printSequence2 (int number)
    {
        System.out.print("Sequence2: ");
        for(int i = 1; i < number; i++)
        {
            String vorzeichen = i % 2 == 0 ? "-" : " ";
            System.out.print(vorzeichen+i+" ");
        }
        System.out.println(" ");
    }
    //Aufgabe 3c
    public static void printSequence3 (int number)
    {
        System.out.print("Sequence3:  ");
        int adder = 1;
        for(int i = 0; i < number; i++)
        {
            System.out.print((i+adder++)+" ");
            adder = adder + i;
        }
        System.out.println(" ");
    }
    //Aufgabe 4
    public static void printTriangle (int number)
    {
        System.out.println("\nVisualizes the "+number+"-th triangular number:");
        for(int i = 0; i < number; i++)
        {
            System.out.print(". ".repeat(i+1).indent(number-i));
        }
    }
}
