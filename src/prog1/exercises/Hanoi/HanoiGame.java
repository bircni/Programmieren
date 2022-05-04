package prog1.exercises.Hanoi;

import java.util.ArrayList;
import java.util.List;

public class HanoiGame {
    public static class Stab {
        private List<Integer> l = new ArrayList<>();

        public Stab(int n) {
            for (int i = n; i > 0; i--) {
                pushi(i);
            }
        }

        public int popi() {
            return l.remove(l.size() - 1);
        }

        public void pushi(int i) {
            l.add(i);
        }

        public int getSize(int x) {
            if (x < l.size()) {
                return l.get(x);
            }
            return 0;
        }
    }

    private int n;
    private Stab[] staebe;

    public HanoiGame(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        staebe = new Stab[] { new Stab(n), new Stab(0), new Stab(0) };
    }

    public void print() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int x = n - i - 1;
            for (Stab stab : staebe) {
                s.append(getDisc(x, stab));
            }
            s.append("\n");
        }
        System.out.println(s);
    }

    private String getDisc(int x, Stab s) {
        StringBuilder b = new StringBuilder();
        int n2 = s.getSize(x);
        int n3 = n - n2 + 2;
        b.append(" ".repeat(Math.max(0, n3)));
        b.append("xx".repeat(Math.max(0, n2)));
        b.append(" ".repeat(Math.max(0, n3)));
        return b.toString();
    }

    public void bewege() {
        bewege(n, staebe[0], staebe[1], staebe[2]);
    }

    private void bewege(int i, Stab a, Stab b, Stab c) {
        if (i > 0) {
            bewege(i - 1, a, c, b);
            c.pushi(a.popi());
            print();
            bewege(i - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
        HanoiGame m = new HanoiGame(4);
        m.bewege();
    }
}