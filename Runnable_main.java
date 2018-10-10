//Problem - 2

package LSDA_Assignment1;

/**
 * This class carries out the bucket sort algorithm in parallel using Multihreading.
 *
 * @author Chandana Dasari
 *
 */

import java.util.ArrayList;


public class Runnable_main {

    public static int[] bucketSort (int[] numbers, int bucketCount) {

        if (numbers.length <= 1) return numbers;
        int maxVal = numbers[0];
        int minVal = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxVal) maxVal = numbers[i];
            if (numbers[i] < minVal) minVal = numbers[i];
        }

        double interval = ((double) (maxVal - minVal + 1)) / bucketCount; // range of bucket
        ArrayList<Integer> buckets[] = new ArrayList[bucketCount];

        for (int i = 0; i < bucketCount; i++) // initialize buckets (initially empty)
            buckets[i] = new ArrayList<Integer>();

        for (int i = 0; i < numbers.length; i++) // distribute numbers to buckets
            buckets[(int) ((numbers[i] - minVal) / interval)].add(numbers[i]);

        for (int i = 0; i < buckets.length; i++) {

            Runnable ob = new bucketSort_Parallel(buckets[i]) ;
            Thread thread = new Thread(ob);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        int k = 0;

        for (int i = 0; i < buckets.length; i++) { // update array with the bucket content
            for(int j =0; j < buckets[i].size(); j++) {
                numbers[k] = buckets[i].get(j);
                k++ ;
            }
        }

        return numbers;
    }



    static void printArray(int arr[]) //function for printing the array elements
    {
        for (int i=0; i<arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {

        int []numbers = {8,6,1,3,22,100,0,1200,5000,20,120,66,96,87,45,12,10,60,89,98,92,72,81,33,18,50,14} ;
        int bucketCount = 10;
        System.out.println("Array before Bucket sort :");
        printArray(numbers);
        int []arr = bucketSort(numbers,bucketCount);
        System.out.println("Array after Bucket sort :");
        printArray(arr);

    }
}

/* Output :
Array before Bucket sort :
8 6 1 3 22 100 0 1200 5000 20 120 66 96 87 45 12 10 60 89 98 92 72 81 33 18 50 14
Array after Bucket sort :
0 1 3 6 8 10 12 14 18 20 22 33 45 50 60 66 72 81 87 89 92 96 98 100 120 1200 5000

Array before sort :
22 45 12 8 10 6 502 72 81 33 18 50 14 125
Array after Bucket sort
6 8 10 12 14 18 22 33 45 50 72 81 125 502

Array before sort :
76 92 12 3 54 86 27 30 10 91 67 3 91 1 65 53 23 230 800 169
Array after Bucket sort
1 3 3 10 12 23 27 30 53 54 65 67 76 86 91 91 92 169 230 800
 */

