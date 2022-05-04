// TEAMARBEIT Fabian Lippold & Nicolas Bircks

package prog2.exercises.set05;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Tank {
    final int PI = 3;
    final Set<Tank> parts;
    final int volume, surface;

    public Tank() {
        this.volume = 0;
        this.surface = 0;
        this.parts = new LinkedHashSet<>();
    }

    public Tank(int width, int height, int depth, TankType t) {
        switch (t) {
            case Cuboid -> {
                this.volume = width * height * depth;
                this.surface = (width * height) * 4 + (height * depth) * 2;
            }
            case Cylindrical -> {
                this.volume = PI * (depth / 2) * (depth / 2) * width;
                this.surface = width * 2 * PI * (depth / 2);
            }
            case Spherical -> {
                this.volume = (4 * PI * (depth / 2) * (depth / 2) * (depth / 2)) / 3;
                this.surface = 4 * PI * (depth / 2) * (depth / 2);
            }
            default -> {
                this.volume = 0;
                this.surface = 0;
            }
        }
        this.parts = new LinkedHashSet<>();
    }

    private Tank(int volume, int surface, Set<Tank> tanks) {
        this.volume = volume;
        this.surface = surface;
        this.parts = new LinkedHashSet<>(tanks);
    }

    public int sumVolume() {
        return this.volume + parts.stream().mapToInt(Tank::sumVolume).sum();
    }

    public int sumSurface() {
        return this.surface + parts.stream().mapToInt(Tank::sumSurface).sum();
    }

    public Iterator<Tank> tankIterator() {
        return Stream.of(Set.of(this), parts).flatMap(Collection::stream).collect(Collectors.toSet()).iterator();
    }

    @Override
    protected Tank clone() {
        return new Tank(
                this.volume,
                this.surface,
                this.parts
        );
    }

    public Tank add(Tank part) {
        return new Tank(
                this.volume,
                this.surface,
                Stream.of(parts, Set.of(part)).flatMap(Collection::stream).collect(Collectors.toSet())
        );
    }

    public Tank rem(Tank part) {
        return new Tank(
                this.volume,
                this.surface,
                Stream.of(parts, Set.of(part)).flatMap(Collection::stream).filter(x -> x != part).collect(Collectors.toSet())
        );
    }

    @Override
    public String toString() {
        return "Tank with (Volume, Surface): (" + this.sumVolume() + ", " + this.sumSurface() + ")\nChilds: [" + this.parts.stream().map(x -> "Tank (" + x.volume + ", " + x.surface + ")").collect(Collectors.joining(", ")) + "]";
    }
}