package prog2.exercises.set06;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class WordFrequencyCounter {
    static ArrayList<Words> words = new ArrayList<>();
    int linecount;
    int wordcount;

    public static void main(String[] args) {
        WordFrequencyCounter counter = new WordFrequencyCounter();
        counter.analyzeText(new File("data/Song.txt"));
        counter.printResults();
        counter.countParts();
    }

    public void analyzeText(File textFile) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(textFile)); // Iterate through each line of the file
            while (true) {
                String currLine = in.readLine();
                if (currLine == null) break;
                analyzeLine(currLine);
            }
        } catch (IOException ex) {
            System.out.println("Error occurred while reading from " + textFile.getAbsolutePath() + ": \n" + ex);
        }
    }

    private void analyzeLine(String line) {
        wordcount += new StringTokenizer(line).countTokens();
        if (!line.equals("")) linecount++;
        analyzeWords(line);
    }

    private void countParts() {
        Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL;
        try {
            oURL = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            desktop.browse(oURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nYou just got rickrolled!");
    }

    private String clearWords(String word) {
        return word.replaceAll("\\(", "").replaceAll("\\)", "")
                .replaceAll(",", "").replaceAll(":", "")
                .replaceAll("\\.", "").replaceAll("!", "")
                .replaceAll("\\?", "").replaceAll("'", "")
                .replaceAll(";", "").replaceAll("_", "")
                .toLowerCase(Locale.ROOT);
    }

    private void analyzeWords(String line) {
        String[] wordArray = line.trim().split("\s+");
        for (String s : wordArray) {
            s = clearWords(s);
            boolean exists = false;
            for (Words w : words) {
                if (s.equals(w.getInhalt())) {
                    w.increment();
                    exists = true;
                }
            }
            if (!exists) words.add(new Words(s));
        }
    }

    public void printResults() {
        System.out.println("Wordcount: " + wordcount);
        System.out.println("Linecount: " + linecount);
        Collections.sort(words);
        int count = 0;
        for (Words w : words) {
            System.out.format("%15s: %3d", w.getInhalt(), w.getCount());
            if (count % 7 == 6) {
                System.out.println();
            }
            count++;
        }
    }
}