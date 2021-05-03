import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.RecursiveAction;

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

    void merge( int lo, int mid, int hi) {
        ArrayList<Integer> helper = new ArrayList<Integer>(numbers);
        int helperLeft = lo;
        int helperRight = mid + 1;
        int current = lo;

        while (helperLeft <= mid && helperRight <= hi) {
            if (helper.get(helperLeft) <= helper.get(helperRight)) {
                numbers.set(current, helper.get(helperLeft++));
            } else {
                numbers.set(current, helper.get(helperRight++));
            }
            current++;
        }

        while (helperLeft <= mid) {
            numbers.set(current++, helper.get(helperLeft++));
        }
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
