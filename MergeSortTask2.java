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
            merge(lo,mid,hi);
        }
    }

    void merge( int lo, int mid, int hi)
    {
        int n1 = mid - lo + 1;
        int n2 = hi - mid;
        ArrayList<Integer> left = new ArrayList<Integer>(n1);
        ArrayList<Integer> right = new ArrayList<Integer>(n2);

        for (int i = 0; i < n1; ++i)
            left.set(i, numbers.get(lo + i));
        for (int j = 0; j < n2; ++j)
            right.set(j, numbers.get(mid+1+j));

        int i = 0, j = 0;
        int k = lo;

        while (i < n1 && j < n2) {
            if (left.get(i) <= right.get(j)) {
                numbers.set(k, left.get(i++));
            }
            else {
                numbers.set(k, right.get(j++));
            }
            k++;
        }

        while (i < n1)
            numbers.set(k++, left.get(i++));

        while (j < n2)
            numbers.set(k++, right.get(j++));
    }

    @Override
    protected void compute() {
            mergesort(lo, hi);
    }

}
