package prog1.Klausur;

//import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Klausur {
    static Random rd = new Random();

    public static void main(String[] args) {
        int[] arr = new int[10];
        fillarray(arr);
        ReturnArray(arr);
        System.out.println();
        System.out.println("Insertionsort");
        Insertionsort(arr);
        ReturnArray(arr);
        System.out.println();
        System.out.println("Insertionsort2");
        shuffle(arr);
        Insertionsort2(arr);
        ReturnArray(arr);
    }

    private static void shuffle(int [] b) {
        for (int i = b.length - 1; i > 0; i--) {
            int j = rd.nextInt(i + 1);
            int temp = b[i];
            b[i] = b[j];
            b[j] = temp;
        }
    }
    /*
    public static int potenziere_rek(int a, int b) {
        if (b == 0) {
            return 1;
        } else {
            return a * potenziere_rek(a, b - 1);
        }
    }
    */

    public static void fillarray(int[] array) {
        int min = 0;
        int max = array.length * 2;
        for (int i = 0; i < array.length; i++) {
            array[i] = min + (int) (Math.random() * ((max - min) + 1));
        }

    }

    public static void Insertionsort(int[] array) {
        int t;
        for (int i = 1; i < array.length; i++) {
            t = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > t) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = t;
            System.out.println();
            ReturnArray(array);
        }
    }

    public static void Insertionsort2(int[] array) {
        int t;
        for (int i = 1; i < array.length; i++) {
            t = array[i];
            int j = i;
            for (int k = 1; k < array.length; k++) {
                if (j > 0) {
                    if (array[j - 1] > t) {

                        array[j] = array[j - 1];
                        j--;

                    }
                }
            }
            array[j] = t;
            System.out.println();
            ReturnArray(array);
        }
    }

    public static void ReturnArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}

