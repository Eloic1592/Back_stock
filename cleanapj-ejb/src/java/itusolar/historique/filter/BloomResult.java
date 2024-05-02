package itusolar.historique.filter;

import java.util.Arrays;

public class BloomResult {
    boolean in;
    int[] index;

    public BloomResult(boolean in, int[] index) {
        this.setIn(in);
        this.setIndex(index);
    }

    @Override
    public String toString() {
        return "BloomResult{" +
                "in=" + in +
                ", index=" + Arrays.toString(index) +
                '}';
    }

    public int[] getIndex() {
        return index;
    }

    public void setIndex(int[] index) {
        this.index = index;
    }

    public boolean isIn() {
        return in;
    }

    public void setIn(boolean in) {
        this.in = in;
    }
}
