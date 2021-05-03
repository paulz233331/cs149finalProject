import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

/**
 * QuickSortTask2: runs quicksort on a sublist.
 */
public class QuickSortTask2 extends RecursiveAction {
    ArrayList<Integer> numbers;
    private int lo, hi;

    QuickSortTask2(ArrayList<Integer> numbers, int lo, int hi) {
        this.numbers = numbers; this.lo = lo; this.hi = hi;
    }

    QuickSortTask2(ArrayList<Integer> numbers) { this(numbers, 0, numbers.size()-1);};

    protected void quickSort(int lo, int hi)
    {
        if (lo < hi)
        {
            int pi = partition(lo, hi);
            quickSort( lo, pi - 1);
            quickSort( pi + 1, hi);
        }
    }

    @Override
    protected void compute() {
        int p = partition(lo, hi);
        quickSort(lo, p-1);
        quickSort(p+1, hi);
    }

    public int partition(int lo, int hi) {
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
