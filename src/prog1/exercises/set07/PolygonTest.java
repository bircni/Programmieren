package prog1.exercises.set07;

public class PolygonTest {

    public static void main(String[] k){
        Polygon p = new Polygon(0, 0, 9, 0, 9, 9, 0, 9);
        System.out.println("Polygon: " + p.toString());
        System.out.println("Area1: " + p.computeArea1());
        System.out.println("Area2: " + p.computeArea2());
        p.smooth();
        System.out.println("Smooth: " + p.toString());
        System.out.println();

        p = new Polygon(-1, 0, 0, 0, 0, 1, -1, 1);
        System.out.println("Polygon: " + p.toString());
        System.out.println("Area1: " + p.computeArea1());
        System.out.println("Area2: " + p.computeArea2());

        p = new Polygon(-6, 1, 6, 1, 0, 3);
        System.out.println("Polygon: " + p.toString());
        System.out.println("Area1: " + p.computeArea1());
        System.out.println("Area2: " + p.computeArea2());
    }

}