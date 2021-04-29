import org.junit.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import static org.junit.Assert.*;

/**
 * Unit Testing for the QuickSort and MergeSort multithreaded programs.
 */
public class SortTaskTester {

    static final int SIZE = 10000;

    /**
     * Test: Runs quicksort on an array under threshold.
     */
    @Test
    public void testQuickSortSmall() {
        ArrayList<Integer> numbers = new ArrayList<Integer>(SIZE);
        Random rand = new Random();
        for (int i = 1; i < 100; i++) {
            int randomNum = rand.nextInt((100) + 1);
            numbers.add(randomNum);
        }
        ArrayList<Integer> numbers2 = new ArrayList<Integer>(numbers);
        ForkJoinPool pool = new ForkJoinPool();
        QuickSortTask sortTask = new QuickSortTask(numbers);
        long start = System.currentTimeMillis();
        pool.invoke(sortTask);
        long end = System.currentTimeMillis();
        pool.shutdown();
        System.out.println("The execution time of multithreaded quicksort on small array is "
                            + (end - start) + " ms" );
        start = System.currentTimeMillis();
        Collections.sort(numbers2);
        end = System.currentTimeMillis();
        System.out.println("The execution time of quicksort on small array is "
                + (end - start) + " ms" );
        assertTrue(numbers.equals(numbers2));
    }

    /**
     * Test: Runs quicksort on an array over threshold.
     */
    @Test
    public void testQuickSort() {
        ArrayList<Integer> numbers = new ArrayList<Integer>(SIZE);
        Random rand = new Random();
        for (int i = 1; i < SIZE; i++) {
            int randomNum = rand.nextInt((SIZE) + 1);
            numbers.add(randomNum);
        }
        ArrayList<Integer> numbers2 = new ArrayList<Integer>(numbers);
        ForkJoinPool pool = new ForkJoinPool();
        QuickSortTask sortTask = new QuickSortTask(numbers);
        long start = System.currentTimeMillis();
        pool.invoke(sortTask);
        long end = System.currentTimeMillis();
        pool.shutdown();
        System.out.println("The execution time of multithreaded quicksort"
                + " on an array of 10,000 values is "
                + (end - start) + " ms" );
        start = System.currentTimeMillis();
        Collections.sort(numbers2);
        end = System.currentTimeMillis();
        System.out.println("The execution time of quicksort on an array of 10,000 values is "
                + (end - start) + " ms" );
        assertTrue(numbers.equals(numbers2));
    }

    /**
     * Test: Runs mergesort on an array under threshold.
     */
    @Test
    public void testMergeSortSmall(){
        ArrayList<Integer> numbers = new ArrayList<Integer>(SIZE);
        Random rand = new Random();
        for (int i = 1; i < 100; i++) {
            int randomNum = rand.nextInt((100) + 1);
            numbers.add(randomNum);
        }
        ArrayList<Integer> numbers2 = new ArrayList<Integer>(numbers);
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask sortTask = new MergeSortTask(numbers);
        long start = System.currentTimeMillis();
        pool.invoke(sortTask);
        long end = System.currentTimeMillis();
        pool.shutdown();
        System.out.println("The execution time of multithreaded mergesort"
                + " on a small array is "
                + (end - start) + " ms" );
        start = System.currentTimeMillis();
        Collections.sort(numbers2);
        end = System.currentTimeMillis();
        System.out.println("The execution time of mergesort on a small array is "
                + (end - start) + " ms" );
        assertTrue(numbers.equals(numbers2));
    }

    /**
     * Test: Runs mergesort on an array over threshold.
     */
    @Test
    public void testMergeSort(){
        ArrayList<Integer> numbers = new ArrayList<Integer>(SIZE);
        Random rand = new Random();
        for (int i = 1; i < SIZE; i++) {
            int randomNum = rand.nextInt((SIZE) + 1);
            numbers.add(randomNum);
        }
        ArrayList<Integer> numbers2 = new ArrayList<Integer>(numbers);
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask sortTask = new MergeSortTask(numbers);
        long start = System.currentTimeMillis();
        pool.invoke(sortTask);
        long end = System.currentTimeMillis();
        pool.shutdown();
        System.out.println("The execution time of multithreaded mergesort"
                + " on an array of 10,000 values is "
                + (end - start) + " ms" );
        start = System.currentTimeMillis();
        Collections.sort(numbers2);
        end = System.currentTimeMillis();
        System.out.println("The execution time of mergesort on an array of 10,000 values is "
                + (end - start) + " ms" );
        assertTrue(numbers.equals(numbers2));
    }

}
