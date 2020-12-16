package com.company;

/**
 * Class to input an int array and run QuickSort analysis based on 4 cases
 * Case 1: 1st item is pivot, stopping case of 1 or 2
 * Case 2: 1st item is pivot, stopping case of 100, followed by insertion sort
 * Case 3: 1st item is pivot, stopping case of 50, followed by insertion sort
 * Case 4: median of 3 is pivot, stopping case of 1 or 2
 *
 */
public class QuickSort {

    /**
     * Driver method to run QuickSort based on input from Driver.java
     *
     * @param arr
     * @param low
     * @param high
     * @param userChoice
     * @return
     */
    public  int[] sortData (int[]arr, int low, int high, int userChoice) {
        int choice = userChoice;
        int arrLength = arr.length;
        int stoppingCase = stoppingCase(choice);

        if (arrLength >= stoppingCase) {
            quickSort(arr, low, high, choice);
        } else {
            insertionSort(arr);
        }
        return arr;  //return array
    }  //end method sort Data

    /**
     * QuickSort method to divide- and- conquer an array, with partition based on scenario
     * @param arr
     * @param low
     * @param high
     * @param choice
     */
    public  void quickSort(int arr[], int low, int high, int choice) {
        if (arr == null) {  //check for empty array
            System.out.println("empty array");
            return;
        }
        if (low >= high) {
            return;
        }


        int pivot = arr[choosePivot(choice, low, high)];  //call choosePivot() to find pivot based on case
        int arrLength = arr.length;
        int stoppingCase = stoppingCase(choice);  //call stoppingCase() to find stopping case based on case

        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {  //check until all values to left of array are lower than pivot
                    i++;  //iterate to next array index
                }
            while (arr[j] > pivot) { //check until all values to right of array are higher than pivot
                    j--; //decrement array index
                }
            if (i <= j) {// compare values from both sides of lists to see if we need swapping
                    swap(arr, i, j);
                    i++;  //move iterators post swap
                    j--;
                }
            } //end while

            //recursive calls to sort sub arrays
        if (arrLength >= stoppingCase) {
            //System.out.println("Quick Sort");
            if (low < j) {
                quickSort(arr, low, j, choice);
            }
            if (high > i) {
                quickSort(arr, i, high, choice);
            } else {
               // System.out.println("Insertion Sort");
                insertionSort(arr);
            } //end else
        }//end outer if
    }//end method quickSort


    /**
     * Utility method to return stopping case based on case being read by Driver method
     *
     * @param choice
     * @return
     */

    public static int stoppingCase ( int choice){
        int userChoice = choice;
        int stoppingCase = 0;

        switch (userChoice) {
            case 1: case 4:  //low stopping case of 2
                stoppingCase = 2;
                break;
            case 2: //Stopping case of 100
                stoppingCase = 100;
                break;
            case 3: //Stopping case of 50
                stoppingCase = 50;
                break;
            default: //Default
                System.out.println("Not a valid choice");
                System.exit(0);
                break;
        }
        return stoppingCase;  //return stopping Case

    }//end method stopping case


    /**
     * Utility method to return Pivot value in QuickSort based on user choice
     *
     * @param choice
     * @param low
     * @param high
     * @return
     */

    public static int choosePivot(int choice, int low, int high) {
        int userChoice = choice;
        int pivot = 0;
        switch (userChoice) {
            case 1: case 2: case 3:  //Choose first value of array as pivot
                pivot = low;
                break;
            case 4:  //choose median of 3 values as pivot
                pivot = low + (high - low) / 2;
                break;
            default:  //default
                System.out.println("not a valid choice");
                System.exit(0);
                break;
        }
        return pivot;  //return pivot
    }  //end method choosePivot();

    /**
     * Utility method to swap values in a given array
     * @param arr
     * @param x
     * @param y
     */

    public static void swap(int arr[], int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    } //end method swap

    /**
     * Utility method for writing array to screen
     * @param arr
     */
    public static void  printArray(int arr[]) {

        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();  //formatting
    }  //end method printArray

    /**
     * Simple insertion sort, to be called for on cases 2  & 3
     *  * Case 2: 1st item is pivot, stopping case of 100, followed by insertion sort
     *  * Case 3: 1st item is pivot, stopping case of 50, followed by insertion sort
     * @param a
     */
    public void insertionSort(int[] a) {
        int n = a.length;

        for (int i = 0; i < n; i++) {  //iterate through array
            int key = a[i];  //set key to index
            int j = i -1;  //set j to previous value

            while (j >= 0 && a[j] > key) {  //compare array value with predecessor value, swap lower value to front of array
                a[j + 1] = a[j];
                j = j -1;
            }  //end while loop
            a[j + 1] = key;  //next value
        } //end for loop

    }//end method insertionSort();

}//end class QuickSort

