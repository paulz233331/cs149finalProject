import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.RecursiveAction;

/**
 * MergeSortTask2: runs mergesort on a sublist.
 */
public class MergeSortTask2 extends RecursiveAction {
    ArrayList<Integer> numbers;
    private int lo;
    private int hi;
    private int mid;

    MergeSortTask2(ArrayList<Integer> numbers, int lo, int hi) {
        this.numbers = numbers; this.lo = lo; this.hi = hi;
        this.mid = (lo + hi) >>> 1;
    }

    MergeSortTask2(ArrayList<Integer> numbers) { this(numbers, 0, numbers.size()-1);};

    protected void mergesort(int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            mergesort(lo, mid);
            mergesort( mid+1 , hi);
        }
    }

    @Override
    protected void compute() {
            mergesort(lo, hi);
    }

}
