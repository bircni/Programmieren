package prog1.exercises.set09;

import rl.prog1.util.painttool.AbstractController;
import rl.prog1.util.painttool.PaintTool;

import java.util.ArrayList;

public class SortingToolController extends AbstractController {
    Main m;

    @Override
    public String[] getButtonNames() {
        return new String[]{
                "Quicksort",
                "Heapsort",
                "Mergesort",
                "Bubblesort",
                "Selectionsort",
                "Insertionsort",
                "Shuffle",
                "Play",
                "Stop",
                "                                                                                                      " +
                        "                                                                                              "
        };
    }

    @Override
    public String getTitle() {
        return "Sorting Tool";
    }

    @Override
    public void onButtonPressed(PaintTool ptool, int button) {
        if (!m.active.ready) {
            return;
        }
        switch (button) {
            case 0 -> m.setAlgorithm(new Quicksort(new ArrayList<>(m.getData()), m));
            case 1 -> m.setAlgorithm(new Heapsort(new ArrayList<>(m.getData()), m));
            case 2 -> m.setAlgorithm(new Mergesort(new ArrayList<>(m.getData()), m));
            case 3 -> m.setAlgorithm(new Bubblesort(new ArrayList<>(m.getData()), m));
            case 4 -> m.setAlgorithm(new Selectionsort(new ArrayList<>(m.getData()), m));
            case 5 -> m.setAlgorithm(new Insertionsort(new ArrayList<>(m.getData()), m));
            case 6 -> m.shuffle();
            case 7 -> m.play();
            case 8 -> m.stop();
        }
    }

    public SortingToolController(Main m) {
        this.m = m;
    }
}

