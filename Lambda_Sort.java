//Problem 3
package LSDA_Assignment1;

import java.util.ArrayList;
import java.util.Collections;

public class Lambda_Sort {
    public static int[] bucketSort ( int[] numbers, int bucketCount, myLambda my_Sort ){

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

        int k = 0;

        for (int i = 0; i < buckets.length; i++) {
            my_Sort.sort_function(buckets[i]); // calls lambda function

            for (int j = 0; j < buckets[i].size(); j++) { // update array with the bucket content
                numbers[k] = buckets[i].get(j);
                k++;
            }
        }

        return numbers;
    }



    static void printArray(int arr[])
    {
        for (int i=0; i<arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {

        int []numbers = {1000,49,0,78,66,92122,45,12,8,10,6,502,72,81,33,18,50,14,125} ;
        int bucketCount = 10;

        //lambda function definition
        myLambda my_Sort = (x) -> {
            Collections.sort(x) ;
        };
        System.out.println("Array before Bucket sort");
        printArray(numbers);

        int []arr = bucketSort(numbers,bucketCount, my_Sort);

        System.out.println("Array after Bucket sort");
        printArray(arr);

    }
    //Interface for lambda
    public interface  myLambda{
        public void sort_function(ArrayList<Integer> x);
    }
}

/* Output :
Array before Bucket sort
22 45 12 8 10 6 502 72 81 33 18 50 14 125
Array after Bucket sort
6 8 10 12 14 18 22 33 45 50 72 81 125 502

Array before Bucket sort :
76 92 12 3 54 86 27 30 10 91 67 3 91 1 65 53 23 230 800 169
Array after Bucket sort
1 3 3 10 12 23 27 30 53 54 65 67 76 86 91 91 92 169 230 800

Array before Bucket sort
1000 49 0 78 66 92122 45 12 8 10 6 502 72 81 33 18 50 14 125
Array after Bucket sort
0 6 8 10 12 14 18 33 45 49 50 66 72 78 81 125 502 1000 92122

 */