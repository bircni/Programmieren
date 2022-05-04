package prog1.exercises.set03;
import java.time.YearMonth;
import rl.prog1.util.terminal.Terminal;
public class ExerciseSet03 extends Terminal{
    public static void main(String[] args) {
        //ausgabe();
        //planProject();
        getDayOfTheWeek(11, 9, 2001);
        //getDayOfTheWeek2(11,9,2001);
        printCalendar(10,2021);
    }

    //Aufgabe 1
    public static void ausgabe() {
        for (int i = 1; i <= 3; i++)
            System.out.println("main");
        int m = readInt("Monat");
        int y = readInt("Jahr");
        System.out.println("Der Monat hat " + getDaysOfMonth(m, y) + " Tage.");
    }

    //Aufgabe 2
    public static void planProject() {
        //System.out.print("\nProjektumfang? (in Arbeitsstunden)");
        int arbeit = readInt("Projektumfang? (in Arbeitsstunden)");
        double stundensatz = readDouble("Stundensatz");
        int mitarbeiter = readInt("Wie viele Mitarbeiter sind verfügbar");
        double kosten = arbeit * stundensatz;
        int tage = (int) Math.ceil((double) arbeit / (8 * mitarbeiter));
        System.out.println("Kosten :" + kosten + "€" + "\nArbeitstage: " + tage);
    }

    //Aufgabe 3
    public static int getDaysOfMonth(int month, int year) {
        if (month < 8) {
            if (month == 2) {
                if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            } else {
                if (month % 2 == 0) {
                    return 30;
                } else {
                    return 31;
                }
            }
        } else {
            if (month % 2 == 0) {
                return 31;
            } else {
                return 30;
            }
        }
    }
    //Aufgabe 4
    public static int getDayOfTheWeek(int day, int month, int year) {
        int year_front = year / 100;
        int year_back = year - (year_front * 100);
        if (month < 3) {
            month += 12;
            year -= 1;
            year_front = year / 100;
            year_back = year - (year_front * 100);
        }
        int q = day;
        int m = month;
        int J = year_front;
        int K = year_back;
        int day_code = (q + (int) Math.floor(((m + 1) * 13) / 5) + K + (int) Math.floor(K / 4) + (int) Math.floor(J / 4) - 2 * J) % 7;
        day_code -= 1;
        if (day_code < 1) {
            day_code += 7;
        }
        //System.out.println(day_code); //Debugger
        switch (day_code) {
            case 1 -> System.out.println("Montag");
            case 2 -> System.out.println("Dienstag");
            case 3 -> System.out.println("Mittwoch");
            case 4 -> System.out.println("Donnerstag");
            case 5 -> System.out.println("Freitag");
            case 6 -> System.out.println("Samstag");
            case 7 -> System.out.println("Sonntag");
        }
        return day_code;

    }
    public static int getDayOfTheWeek2(int day, int month, int year){
        YearMonth datum = YearMonth.of(year,month);
        return datum.atDay(day).getDayOfWeek().ordinal()+1;
    }
    //Aufgabe 5
    public static void printCalendar(int month, int year) {
        System.out.println(getDaysOfMonth(month, year));
        int start = YearMonth.of(year, month).atDay(1).getDayOfWeek().ordinal();
        System.out.print("Mo Tu We Th Fr Sa Su\n" + "   ".repeat(start) + "\b");

        for (int i = 1; i <= getDaysOfMonth(month, year); i++) {
            System.out.print(" ".repeat(3 - String.valueOf(i).length()));
            System.out.print("\b".repeat(Boolean.compare((i + start - 1) % 7 == 0, true) + 1) + i + "\n".repeat(Boolean.compare((i + start) % 7 == 0, true) + 1));
        }

    }
}
