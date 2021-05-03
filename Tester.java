import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.Random;
import java.util.Arrays;

/**
 * Tester runs the QuickSort and MergeSort multithreaded programs on array with 100,000 values.
 */
public class Tester {

    static final int SIZE = 100000;

    public static void main(String[] args) {
        /*test quicksort*/
        ArrayList<Integer> numbers = new ArrayList<Integer>(SIZE);
        Random rand = new Random();

        for (int i = 1; i < SIZE; i++) {
            int randomNum = rand.nextInt((SIZE) + 1);
            numbers.add(randomNum);
        }
        ForkJoinPool pool = new ForkJoinPool();
        QuickSortTask sortTask = new QuickSortTask(numbers);
        long start = System.currentTimeMillis();
        pool.invoke(sortTask);
        long end = System.currentTimeMillis();
        pool.shutdown();
        System.out.println("The execution time of multithreaded quicksort on array of 100,000 values is "
                + (end - start) + " ms" );
        /*test mergesort*/
        numbers.clear();

        for (int i = 1; i < SIZE; i++) {
            int randomNum = rand.nextInt((SIZE) + 1);
            numbers.add(randomNum);
        }
        pool = new ForkJoinPool();
        MergeSortTask sortTask2 = new MergeSortTask(numbers);
        start = System.currentTimeMillis();
        pool.invoke(sortTask2);
        end = System.currentTimeMillis();
        pool.shutdown();
        System.out.println("The execution time of multithreaded mergesort on array of 100,000 values is "
                + (end - start) + " ms" );
    }
}