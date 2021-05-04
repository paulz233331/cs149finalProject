import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.RecursiveAction;

/**
 * MergeSortTask: runs selection sort on small arrays and forks two threads to sort two sublists
 * of larger arrays. Then, merges them.
 */
public class MergeSortTask extends RecursiveAction {
    ArrayList<Integer> numbers;
    private int lo;
    private int hi;
    private int mid;

    static final int THRESHOLD = 100;

    MergeSortTask(ArrayList<Integer> numbers, int lo, int hi) {
        this.numbers = numbers; this.lo = lo; this.hi = hi;
        this.mid = (lo + hi) >>> 1;
    }

    MergeSortTask(ArrayList<Integer> numbers) { this(numbers, 0, numbers.size()-1);};

    @Override
    protected void compute() {
        if (hi - lo <= THRESHOLD)
            selectionSort(lo, hi);
        else {
            MergeSortTask sortTask1 = new MergeSortTask(numbers, lo, mid);
            MergeSortTask sortTask2 = new MergeSortTask(numbers, mid +1, hi);
            sortTask1.fork();
            sortTask2.fork();
            sortTask1.join();
            sortTask2.join();
            merge( lo, mid, hi);
        }
    }

    void merge( int lo, int mid, int hi)
    {
        int n1 = mid - lo + 1;
        int n2 = hi - mid;
        ArrayList<Integer> left = new ArrayList<Integer>(n1);
        ArrayList<Integer> right = new ArrayList<Integer>(n2);

        for (int i = 0; i < n1; ++i)
            left.add(numbers.get(lo + i));
        for (int j = 0; j < n2; ++j)
            right.add(numbers.get(mid+1+j));

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

    void selectionSort(int lo, int hi)
    {
        int i, j, min_idx;
        for (i = lo; i <= hi-1; i++)
        {
            min_idx = i;
            for (j = i+1; j <= hi; j++)
                if (numbers.get(j) < numbers.get(min_idx))
                    min_idx = j;
            swap(min_idx, i);
        }
    }

    public void swap(int index1, int index2)
    {
        Integer temp;
        temp = numbers.get(index1);
        numbers.set(index1, numbers.get(index2));
        numbers.set(index2, temp);
    }
}
