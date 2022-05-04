package prog1.exercises.set06;
//import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import rl.prog1.util.terminal.Terminal;

class BogoRunner extends Thread{
    private int[] array;
    private boolean running = true;
    Random rd = new Random();

    public BogoRunner(int /*@NotNull*/ [] a){
        array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);
    }

    @Override
    public void run(){
        while(!isSorted(array) && running) array = shuffle(array);
    }

    public void setRunning(boolean a){
        running = a;
    }

    public int[] get(){
        return array;
    }

    private boolean isSorted(int /*@NotNull*/ [] a){
        for (int i = 0; i < a.length - 1; i++) if (a[i] > a[i+1]) return false;
        return true;
    }

    private int[] shuffle(int /*@NotNull*/ [] b){
        for (int i = b.length-1; i > 0; i--) {
            int j = rd.nextInt(i+1);
            int temp = b[i];
            b[i] = b[j];
            b[j] = temp;
        }
        return b;
    }
}

public class ExerciseSet06 extends Terminal
{
    public static void main(String[] args) {
        menuBar();
    }

    //Aufgabe 1
    public static int [] zufall(int size, int min, int max)
    {
        int [] zufallarr = new int[size];
        for(int i=0; i<=size-1;i++) {
            zufallarr[i] = min + (int)(Math.random() * ((max - min) + 1));
        }
        return zufallarr;
    }

    //Aufgabe 1
    public static int[] sort(int[] array){
        int cores = Runtime.getRuntime().availableProcessors();
        ArrayList<BogoRunner> l = new ArrayList<>();

        for(int i=0; i<cores; i++){
            l.add(new BogoRunner(array));
            l.get(i).start();
        }

        while(true){for(int i=0; i<cores; i++){if(!l.get(i).isAlive()){for(int t=0; t<cores; t++){l.get(t).setRunning(false);}return l.get(i).get();}}}}

    //Aufgabe 2
    public static void Raten(int min, int max) {
        int a = min + (int) (Math.random() * ((max - min) + 1));
        System.out.println("I found a number between" + min + " and " + max + ". Guess its value ...");
        System.out.print("\nProvide a number: ");
        Scanner s = new Scanner(System.in);
        int guess = 0;
        while (true) {
            try {
                guess = Integer.parseInt(s.nextLine());
            } catch (Exception e) {
                System.out.println("Please provide an integer number!");
            }
            if (guess < a) {
                System.out.println("My number is larger!");
            } else if (guess > a) {
                System.out.println("My number is smaller!");
            }
            else{
                System.out.println("You got it!");
                return;
            }
        }
    }

    //Aufgabe 3
    public static int find(String text, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        return m.find() ? m.start() : -1;
    }

    //Aufgabe 4
    public static void plan(){
        int n = Terminal.readInt("Number of places (-1 for default route)");

        String[] place;
        int[] height;
        int[] dist;
        int uspeed;
        int dspeed;
        int hspeed;

        if(n == -1){
            place = new String[]{"Angeregg", "Hanslettalm", "Wiedersberger_Horn", "Hanslettalm", "Angeregg"};
            height = new int[]{950, 1616, 2127, 1616, 950};
            dist = new int[]{0, 3500, 5000, 7500, 10000};
            n = 5;
        }else{
            place = new String[n];
            height = new int[n];
            dist = new int[n];

            for(int i=0; i<n; i++){
                System.out.println(i+1+". Place");

                System.out.print("\tName\t\t\t\t   :");
                place[i] = Terminal.readString("");

                System.out.print("\tHeight\t\t\t\t[m]  :");
                height[i] = Terminal.readInt("");

                System.out.print("\tDistance from Start\t\t\t\t[m] :");
                dist[i] = Terminal.readInt("");
            }
        }

        uspeed = Terminal.readInt("Upward speed      [m/h] ");
        dspeed = Terminal.readInt("Downward speed    [m/h] ");
        hspeed = Terminal.readInt("Horizontal speed  [m/h]");

        double zeitGesamt = 0;

        System.out.println();
        System.out.println("Routenplanung: ");
        System.out.println(place[0] + ": 0:0");
        for(int i=1; i<n; i++) {
            double timeEben = (dist[i]-dist[i-1]) / (double) hspeed ;
            int hdif = height[i]-height[i-1];
            double timeHoehe = hdif > 0 ? hdif / (double) uspeed  : hdif / (double) -dspeed;
            zeitGesamt += timeHoehe < timeEben ? (timeHoehe/2 + timeEben) : (timeEben/2 + timeHoehe);
            System.out.println(place[i] + ": " + (int) Math.floor(zeitGesamt) + ":" +(int) ((zeitGesamt - Math.floor(zeitGesamt)) * 60));
        }
        System.out.println();
    }

    //Aufgabe 5
    public static void menuBar(){
        System.out.println("1 : Aufgabe 1 (sort) \n2 : Aufgabe 2 (raten)\n3 : Aufgabe 3 (suchen)\n4 : Aufgabe 4 (routenplan)\n0 : Beenden");
        int choice = readInt("Ihre Wahl");

        switch (choice) {
            case 0 -> {
                System.out.println("Thank you for using our services!");
                System.exit(0);
            }
            case 1 -> {
                int[] k = zufall(Terminal.readInt("size"),Terminal.readInt("min"),Terminal.readInt("max"));
                System.out.println("Unsorted:");
                for(int s : k){
                    System.out.print(s +",");
                }
                System.out.println();

                k = sort(k);

                System.out.println();
                System.out.println("Sorted:");
                for(int s : k){
                    System.out.print(s +",");
                }
                System.out.println();
            }
            case 2 -> Raten(1,20);
            case 3 -> {
                String a = readString("Worin suchst du?");
                String b = readString("Was suchst du?");
                System.out.println("First occurance at: " + find(a,b));
            }
            case 4 -> plan();
            default -> System.out.println("Please provide a suitable number");
        }
        menuBar();
    }
}
