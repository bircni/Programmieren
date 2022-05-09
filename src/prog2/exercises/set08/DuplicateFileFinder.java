package prog2.exercises.set08;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateFileFinder {
    public HashMap<String, String> map = new HashMap<>();
    FlexibleFileTreeWalker fftw;

    // Besser als nur Name und Größe zu vergleichen
    public DuplicateFileFinder() {
        fftw = new FlexibleFileTreeWalker(
                f -> map.put((new File(".")
                        .getAbsoluteFile()
                        .toPath()
                        .relativize(f.getAbsoluteFile().toPath())
                        .toString()
                ), new BigInteger(1,
                        MessageDigest
                                .getInstance("SHA-256")
                                .digest(Files.readAllBytes(f.toPath()))
                ).toString(16))
        );
    }

    void walk(String path) throws Exception {
        fftw.walk(path);
        map.entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue))
                .values()
                .stream()
                .filter(x -> x.size() > 1)
                .forEach(
                        x -> System.out.printf("Duplicate hash: %s\n%s\n",
                                x.get(0).getValue(),
                                x.stream().map(y -> String.format("\t- %s\n", y.getKey())).collect(Collectors.joining())
                        )
                );
    }
}
