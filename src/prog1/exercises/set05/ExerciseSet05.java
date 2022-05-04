package prog1.exercises.set05;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.IntStream;
import rl.prog1.util.terminal.Terminal;

public class ExerciseSet05 extends Terminal {

    static DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
    static DecimalFormat df = new DecimalFormat("0.00", dfs);

    public static void main(String[] args) {
        menuBar();
    }

    //Aufgabe 1
    public static void zinsen() {
        double kapital = readInt("Geben Sie das Anfangskapital ein [in Euro]");
        double zinssatz = readInt("Bitte geben Sie den Zinssatz ein [in %]");
        int anlagedauer = readInt("Geben Sie die Anlagedauer ein [in Jahren]");
        for (int i = 1; i <= anlagedauer; i++) {
            System.out.println("Kapital nach " + i + " Jahren: " + new DecimalFormat("0.00").format(kapital * Math.pow((1 + zinssatz / 100), i)) + " €");
        }
    }

    //Aufgabe 2 - Arrays werden hier NICHT ZWINGEND gebraucht!!
    public static void klausur() {
        HashMap<Integer, Double> liste = new HashMap<>();
        IntStream.range(0, readInt("Wie viele Zahlen sollen analysiert werden?")).forEach((i) -> liste.put(i, readDouble("nächste Zahl")));
        System.out.println("OK, die Zahlen lauten: " + liste.values());
        liste.values().stream().min((a, b) -> (int) Math.ceil(a - b)).ifPresent((a) -> System.out.print("Min: " + a));
        liste.values().stream().max((a, b) -> (int) Math.ceil(a - b)).ifPresent((a) -> System.out.print(" Max: " + a));
        liste.values().stream().mapToDouble(i -> i).average().ifPresent((a) -> System.out.println(" Durchschnitt: " + a));
    }

    public static void klausur2() {
        int zahlen = readInt("Wie viele Zahlen sollen analysiert werden");
        double[] noten = new double[zahlen];
        for (int i = 0; i < zahlen; i++) {
            noten[i] = readDouble("nächste Note");
        }
        System.out.println("Please check the numbers!");
        for (int i = 0; i < zahlen; i++) {
            System.out.print(noten[i] + " | ");
        }
        System.out.println();
        double sum = 0;
        for (int i = 0; i < zahlen; i++) {
            sum += noten[i];
        }
        double average = sum / zahlen;
        double min = Arrays.stream(noten).min().getAsDouble();
        double max = Arrays.stream(noten).max().getAsDouble();
        System.out.println("Minimum: " + min + " Maximum: " + max + " Durchschnitt: " + average);
    }

    //Aufgabe 3
    public static double[] mult(double[] vec, double factor) {
        return Arrays.stream(vec).map(i -> i * factor).toArray();
    }

    public static double[] plus(double[] vec1, double[] vec2) {
        return IntStream.range(0, vec1.length).mapToDouble(i -> vec1[i] + vec2[i]).toArray();
    }

    public static double[] minus(double[] vec1, double[] vec2) {
        return IntStream.range(0, vec1.length).mapToDouble(i -> vec1[i] - vec2[i]).toArray();
    }

    public static double vlength(double[] vec) {
        return Math.sqrt(Arrays.stream(vec).map(i -> Math.pow(i, 2)).sum());
    }

    public static double[] rotate2d(double[] vec, int deg) {
        return new double[]{
                Math.cos(Math.toRadians(deg)) * vec[0] - Math.sin(Math.toRadians(deg)) * vec[1],
                Math.sin(Math.toRadians(deg)) * vec[0] + Math.cos(Math.toRadians(deg)) * vec[1]
        };
    }

    public static void vektorentest() {
        double x1 = readDouble("Vektor 1, x");
        double y1 = readDouble("Vektor 1, y");
        double x2 = readDouble("Vektor 2, x");
        double y2 = readDouble("Vektor 2, y");
        double faktor = readDouble("Faktor");
        int winkel = readInt("Winkel");
        System.out.print("v1 * fact\t\t: ( ");
        Arrays.stream(mult(new double[]{x1, y1}, faktor)).forEach(i -> System.out.print(df.format(i) + " "));
        System.out.println(")");
        System.out.print("v1 + v2\t\t\t: ( ");
        Arrays.stream(plus(new double[]{x1, y1}, new double[]{x2, y2})).forEach(i -> System.out.print(df.format(i) + " "));
        System.out.println(")");
        System.out.print("v1 - v2\t\t\t: ( ");
        Arrays.stream(minus(new double[]{x1, y1}, new double[]{x2, y2})).forEach(i -> System.out.print(df.format(i) + " "));
        System.out.println(")");
        System.out.println("vlength(v1)\t\t: " + df.format(vlength(new double[]{x1, y1})));
        System.out.print("rot(v1, deg)\t: ( ");
        Arrays.stream(rotate2d(new double[]{x1, y1}, winkel)).forEach(i -> System.out.print(df.format(i) + " "));
        System.out.println(")");
        System.out.print("next molehill\t: ( ");
        Arrays.stream(haufen(new double[]{x1, y1}, new double[]{x2, y2})).forEach(i -> System.out.print(df.format(i) + " "));
        System.out.println(") [RICHTIG!]");
    }

    //Aufgabe 4
    public static double[] haufen(double[] h1, double[] h2) {
        double[] diff = minus(h2, h1);
        diff = rotate2d(diff, 5);
        diff = mult(diff, 0.99);
        diff = plus(h2, diff);
        return diff;
    }

    //Aufgabe 5
    public static void menuBar() {
        System.out.println("1 : Zinsberechnung");
        System.out.println("2 : Durchschnittsberechnung");
        System.out.println("3 : Vektor-Test");
        System.out.println("4 : Maulwurfsimulation");
        System.out.println("0 : Beenden");
        int choice = readInt("Ihre Wahl");
        if (0 <= choice && choice <= 4) {
            switch (choice) {
                case 0:
                    System.out.println("Thank you for using our service!");
                    System.exit(0);
                case 1:
                    zinsen();
                case 2:
                    System.out.println("Welche Methode willst du verwerden?");
                    System.out.println("0 : mit Arrays");
                    System.out.println("1 : ohne Arrays");
                    int choice2 = readInt("Ihre Wahl");
                    if (choice2 < 2) {
                        switch (choice2) {
                            case 0 -> klausur2();
                            case 1 -> klausur();
                        }
                    }
                case 3:
                    vektorentest();
                case 4:
                    double[] h1 = new double[]{0, 0};
                    double[] h2 = new double[]{1, 0};
                    double[] h3 = null;
                    for (int i = 0; i < 100; i++) {
                        h3 = haufen(h1, h2);
                        h1 = h2;
                        h2 = h3;
                    }
                    System.out.print("Nach 100 Haufen: (");
                    Arrays.stream(h3).forEach(i -> System.out.print(df.format(i) + " "));
                    System.out.println(")");
                default:
                    menuBar();
            }
        } else {
            System.out.println("Please provide a suitable number\n");
            menuBar();
        }
    }

}