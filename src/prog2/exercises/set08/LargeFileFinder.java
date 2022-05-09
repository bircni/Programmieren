package prog2.exercises.set08;

import java.io.File;
import java.text.DecimalFormat;
import java.util.TreeMap;

public class LargeFileFinder {
    FlexibleFileTreeWalker fftw;
    TreeMap<Long, String> output = new TreeMap<>();

    public LargeFileFinder() {
        this.fftw = new FlexibleFileTreeWalker(
                f -> output.put(f.length(), (new File(".").getAbsoluteFile().toPath().relativize(f.getAbsoluteFile().toPath()).toString()))
        );
    }

    public static String readableFileSize(long size) {
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public void walk(String path) throws Exception {
        fftw.walk(path);
        output.forEach((size, name) -> System.out.printf("%10s\t:\t%s\n", readableFileSize(size), name));
    }
}
