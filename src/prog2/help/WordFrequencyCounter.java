package prog2.help;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;


public class WordFrequencyCounter {
    static ArrayList<Texts> text = new ArrayList<>();
    int words;
    int zeilen;

    public static void main(String[] args) {
        WordFrequencyCounter counter = new WordFrequencyCounter();
        counter.analyzeText(new File("DATA/song.txt"));
        counter.printResults();


    }

    public void analyzeText(File textFile) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(textFile));
            // Iterate through each line of the file
            while (true) {
                String currLine = in.readLine();
                if (currLine == null)
                    break;
                analyzeLine(currLine);

            }
        } catch (IOException ex) {
            System.out.println("Error occurred while reading from " + textFile.getAbsolutePath() + ":" + ex);
        }
    }

    private void analyzeLine(String line) {
        words += new StringTokenizer(line).countTokens();
        if (!line.equals("")) zeilen++;
        analyzeWort(line);
        //System.out.println(words+ " "+zeilen);
    }

    private void analyzeWort(String line) {
        String[] wordArray = line.trim().split("\s+");
        for (String s : wordArray) {
            s = cleanText(s);
            boolean exists = false;
            for (Texts w : text) {
                if (s.equals(w.getWord())) {
                    w.counterplus();
                    exists = true;
                }
            }
            if (!exists) text.add(new Texts(s));
        }
        //WARUM???
        //System.out.println(wordArray);
    }

    public void printResults() {
        System.out.println("Wörter: " + words);
        System.out.println("Zeilen: " + zeilen);
        Collections.sort(text);
        int count = 0;
        for (Texts w : text) {
            System.out.format("%15s: %3d", w.getWord(), w.getCount());
            if (count % 7 == 6) {
                System.out.println();
            }
            count++;
        }
    }

    public String cleanText(String wort) {
        return wort.replaceAll("\\.", "").replaceAll(",", "").replaceAll("!", "").replaceAll("\\?", "").toLowerCase(Locale.ROOT);
    }
}


