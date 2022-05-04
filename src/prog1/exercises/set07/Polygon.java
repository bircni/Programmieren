package prog1.exercises.set07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Polygon {
    private ArrayList<Point> points = new ArrayList<>();

    public Polygon(double ... ps){
        IntStream.range(1,ps.length).filter(x -> x % 2 != 0).mapToObj(i -> new Point(ps[i-1], ps[i])).forEach(points::add);
    }

    @SuppressWarnings("unused")
    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> p) {
        this.points = p;
    }

    @SuppressWarnings("unused")
    public void addPoints(double ... ps){
        IntStream.range(1,ps.length).filter(x -> x % 2 != 0).mapToObj(i -> new Point(ps[i-1], ps[i])).forEach(points::add);
    }

    public String toString(){
        return points.toString();
    }

    public double calculateArea1(){
        return Math.abs(IntStream.range(0,points.size()).mapToDouble( i ->
                (points.get(i).getY() + points.get((i + 1) % points.size()).getY()) * (points.get(i).getX() - points.get((i + 1) % points.size()).getX())
        ).sum())/2;
    }
    public double calculateArea2(){
        return Math.abs(IntStream.range(0,points.size()).mapToDouble(i ->
                points.get(i).getX() * (points.get((i + 1) % points.size()).getY() - points.get((points.size() + i - 1)%points.size()).getY())
        ).sum())/2;
    }

    public double computeArea1(){
        return calculateArea1();
    }
    public double computeArea2(){
        return calculateArea2();
    }

    public void smooth(){
        this.setPoints(new ArrayList<>(IntStream.range(0, points.size()).mapToObj(p ->
                Arrays.copyOf(IntStream.range(1, 3).mapToObj(q ->
                        new Point(
                                points.get(p).getX() + (q * (points.get((p + 1) % points.size()).getX() - points.get(p).getX()) / 3),
                                points.get(p).getY() + (q * (points.get((p + 1) % points.size()).getY() - points.get(p).getY()) / 3)
                        )).toArray(), 2, Point[].class)).flatMap(Stream::of).collect(Collectors.toList())));
    }
}