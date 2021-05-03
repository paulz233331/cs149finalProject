import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

/**
 * QuickSortTask: runs selection sort on small arrays and forks two threads to sort two sublists
 * of larger arrays.
 */
public class QuickSortTask extends RecursiveAction {
    ArrayList<Integer> numbers;
    private int lo, hi;

    QuickSortTask(ArrayList<Integer> numbers, int lo, int hi) {
        this.numbers = numbers; this.lo = lo; this.hi = hi;
    }

    QuickSortTask(ArrayList<Integer> numbers) { this(numbers, 0, numbers.size()-1);};

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

    static final int THRESHOLD = 100;

    @Override
    protected void compute() {
        if (hi - lo <= THRESHOLD)
            selectionSort(lo, hi);
        else {
            int p = partition();
            QuickSortTask2 sortTask1 = new QuickSortTask2(numbers, lo, p-1);
            QuickSortTask2 sortTask2 = new QuickSortTask2(numbers, p+1, hi);
            sortTask1.fork();
            sortTask2.fork();
            sortTask1.join();
            sortTask2.join();
        }
    }

    public int partition() {
        int i = lo, j = hi;
        int pivot = (lo + hi) >>> 1;
        swap (pivot, j--);
        while (i <= j) {
            if (numbers.get(i) <= numbers.get(hi)) {
                i++;
                continue;
            }
            if (numbers.get(j) >= numbers.get(hi)) {
                j--;
                continue;
            }
            swap(j--, i++);
        }
        swap(j+1, hi);
        return j+1;
    }

    public void swap(int index1, int index2)
    {
        Integer temp;
        temp = numbers.get(index1);
        numbers.set(index1, numbers.get(index2));
        numbers.set(index2, temp);
    }
}
