package prog2.exercises.set08;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Prints recursively all files which are contained in
 * the current directory or in sub-directories.
 *
 * @author Reinhard Schiedermeier, Ruediger Lunde
 */
public class FlexibleFileTreeWalker {
    private final FileProcessor fp;

    public FlexibleFileTreeWalker(FileProcessor fp) {
        this.fp = fp;
    }

    public void walk(String pathname) throws Exception {
        File[] files = new File(pathname).listFiles();
        if (files != null) {
            for (File file : files)
                if (file.isDirectory())
                    walk(file.getCanonicalPath());
                else
                    process(file);
        }
    }

    protected void process(File file) throws Exception {
        fp.process(file);
    }

    static interface FileProcessor {
        void process(File file) throws NoSuchAlgorithmException, IOException;
    }
}