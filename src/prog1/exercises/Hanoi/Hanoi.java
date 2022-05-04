package prog1.exercises.Hanoi;

public final class Hanoi {

    public static void main(final java.lang.String[] args) {
        verschiebeTurm(3, "A", "C", "B");
    }

    public static void verschiebeTurm(int hoehe,String quelle,String ziel,String hilfsposition) {
        if (hoehe != 0) { /* Schritt 0 */
            verschiebeTurm(hoehe - 1, quelle, hilfsposition, ziel);
            /* Schritt 1 */
            java.lang.System.out.println("Scheibe von " + quelle + " nach " + ziel + " bewegen.");
            /* Schritt 2 */
            verschiebeTurm(hoehe - 1, hilfsposition, ziel, quelle);
        }
    }
}