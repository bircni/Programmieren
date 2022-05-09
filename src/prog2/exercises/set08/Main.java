package prog2.exercises.set08;

public class Main {

    public static void main(String[] args) throws Exception {
        new LargeFileFinder().walk(".");
        System.out.printf("\n%s\n\n", "-".repeat(100));
        new DuplicateFileFinder().walk(".");
    }
}
