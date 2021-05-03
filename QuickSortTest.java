
// Java program for the above approach
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortTest
        extends RecursiveTask<Integer> {

    int start, end;
    int[] arr;

    /**
     * Finding random pivoted and partition
     * array on a pivot.
     * There are many different
     * partitioning algorithms.
     * @param start
     * @param end
     * @param arr
     * @return
     */
    private int partion(int start, int end,
                        int[] arr)
    {

        int i = start, j = end;

        // Decide random pivot
        int pivote = new Random()
                .nextInt(j - i)
                + i;

        // Swap the pivote with end
        // element of array;
        int t = arr[j];
        arr[j] = arr[pivote];
        arr[pivote] = t;
        j--;

        // Start partioning
        while (i <= j) {

            if (arr[i] <= arr[end]) {
                i++;
                continue;
            }

            if (arr[j] >= arr[end]) {
                j--;
                continue;
            }

            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            j--;
            i++;
        }

        // Swap pivote to its
        // correct position
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }

    // Function to implement
    // QuickSort method
    public QuickSortTest(int start,
                                   int end,
                                   int[] arr)
    {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute()
    {
        // Base case
        if (start >= end)
            return null;

        // Find partion
        int p = partion(start, end, arr);

        // Divide array
        QuickSortTest left
                = new QuickSortTest(start,
                p - 1,
                arr);

        QuickSortTest right
                = new QuickSortTest(p + 1,
                end,
                arr);

        // Left subproblem as separate thread
        left.fork();
        right.compute();

        // Wait untill left thread complete
        left.join();

        // We don't want anything as return
        return null;
    }

    // Driver Code
    public static void main(String args[])
    {
        int SIZE = 10000;
        int[] arr = new int [SIZE];

        Random rand = new Random();

        for (int i = 0; i < SIZE; i++) {
            int randomNum = rand.nextInt((SIZE) + 1);
            arr[i] = randomNum;
        }
        int [] copy = Arrays.copyOf(arr, SIZE);
        // Forkjoin ThreadPool to keep
        // thread creation as per resources
        ForkJoinPool pool
                = ForkJoinPool.commonPool();

        // Start the first thread in fork
        // join pool for range 0, n-1
        pool.invoke(
                new QuickSortTest(
                        0, SIZE - 1, arr));

        // Print shorted elements

        //   System.out.print(arr[i] + " ");

        Arrays.sort(copy);

        for(int i=0; i < arr.length; i++){
            if (!(arr[i] == copy[i]))
                System.out.print(arr[i] + ", " + copy[i] + "; ");
        }
        assertArrayEquals(arr,copy);
    }
}