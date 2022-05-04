package prog2.exercises.set01;

import java.util.Arrays;
import java.util.Scanner;

public class ExerciseSet01 {
    static Boolean[] field = new Boolean[42];
    static int user = 0;
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {
        GAME();
    }

    public static void userID() {
        if(user % 2 == 0)   {
            System.out.println("Spieler 1 ("+ANSI_RED+"X"+ANSI_RESET+") ist dran.");
        }
        else{
            System.out.println("Spieler 2 ("+ANSI_BLUE+"X"+ANSI_RESET+") ist dran.");
        }
        user += 1;
    }

    public static void GAME(){
        Scanner s = new Scanner(System.in);
        while (System.nanoTime() >= 0) {
            printField();
            exitIfWon();
            userID();
            System.out.print(ANSI_RESET+"In welche Spalte wollen sie Ihren Stein werfen: ");
            try {
                int n = Integer.parseInt(s.nextLine());
                if (n < 0 || n > 6 || field[n] != null) throw new Exception();
                for (int i = 0; i < 6; i++) {
                    int pos = n + 7 * i;
                    if (field[pos] != null) {
                        field[pos - 7] = Arrays.stream(field).mapToInt(x -> x != null ? 1 : 0).sum() % 2 == 0;
                        break;
                    }
                    if (i == 5)
                        field[pos] = Arrays.stream(field).mapToInt(x -> x != null ? 1 : 0).sum() % 2 == 0;
                }
            } catch (Exception ignored) {
                System.out.println("Dicka ne son scheiß gibts hier nicht, nochmal!");
            }
        }
    }

    public static void exitIfWon() {
        for (int i = 0; i < field.length; i++) {
            int finalI = i;
            if ((i < field.length - 3 && (Arrays.stream((new Boolean[]{field[i], field[i + 1], field[i + 2], field[i + 3]})).allMatch(x -> x != null && x == field[finalI]))) ||
                    (i < field.length - 21 && (Arrays.stream((new Boolean[]{field[i], field[i + 7], field[i + 14], field[i + 21]})).allMatch(x -> x != null && x == field[finalI]))) ||
                    (i < field.length - 24 && (Arrays.stream((new Boolean[]{field[i], field[i + 8], field[i + 16], field[i + 24]})).allMatch(x -> x != null && x == field[finalI]))) ||
                    (i < field.length - 18 && (Arrays.stream((new Boolean[]{field[i], field[i + 6], field[i + 12], field[i + 18]})).allMatch(x -> x != null && x == field[finalI])))) {
                System.out.println("Spieler " + (field[i] ? "1 "+ANSI_RED+"(X)" : "2 "+ANSI_BLUE+"(O)") + ANSI_RESET+" gewinnt!");
                System.exit(0);
            }
        }
    }

    public static void printField() {
        System.out.println("  00 01 02 03 04 05 06");
        System.out.println(" ---------------------");
        System.out.print(" ");
        for (int i = 0; i < field.length; i++) {
            if (field[i] == null) System.out.print(ANSI_RESET + " . ");
            else System.out.print(field[i] ? ANSI_RED +" X " : ANSI_BLUE +" O ");
            if ((i + 1) % 7 == 0) {
                System.out.println();
                System.out.print(" ");
            }
        }
        System.out.println(" ---------------------");
    }
}

//  00 01 02 03 04 05 06
// ---------------------
// . . . . . . .
// . . . . . . .
// . . X O . . .
// . . X X O . .
// . . X X X O .
// . . O O X O O
// ---------------------
