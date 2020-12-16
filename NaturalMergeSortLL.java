package com.company;

/**
 *
 * Class to take in a linked-list and run a NaturalMerge Sort
 *
 *  Natural Merge Sort finds and merges pairs of subsequence that are already ordered.
 * In the sequence 7 4 6 3 1 2 8 5
 * All pairs of sorted subsequences are merged:
 * 7 with 4 6 , 3 with  1 2 8, and 5 is not merged.
 * We use | for separating the sorted subsequences:
 *
 * 7 | 4 6 | 3 |1  2 | 8 | 5
 * 4 6 7 | 3 | 1 2 | 8 | 5
 * 4 6 7   1 2 3 | 8 | 5
 * 4 6 7 1 2 3 5 8
 *
 * 4 6 7 | 1 2 3 | 5 8
 * 1 2 3 4 6 7 | 5 8
 *
 * 1 2 3 4 5 6 7 8
 *
 * The best case for natural merge sort is a sorted array, the running time is O(n).
 * The worst case is O(n log2n).
 */


public class NaturalMergeSortLL {

    /**Takes 2 nodes, compares the values between them, and assigns lower value to "result" node
     *
     * @param a
     * @param b
     * @return
     */

    private List.Node merge (List.Node a, List.Node b) {
        List.Node result;
        //base cases
        if (a == null){
            return b;
        }
        if (b == null){
            return a;
        }


        if ((int) a.getItem() <= (int) b.getItem()){  //compare values of A &B nodes
            result = a;  //input lower value
            result.next = merge(a.next,b);  //recursive call to test next value
        } else { //input lower value
            result = b;
            result.next = merge(a,b.next); //recursive call to test next value
        }
        return result;  //return result node

    }  //end method merge


    /**
     * Method to run a Natural Merge
     * @param h
     * @return
     */

   public List.Node naturalMergeSort(List.Node h) {
       if (h == null || h.next == null) { //base case
           return h;
       }

       List.Node middle = getPartition(h);  // Get highest value is ascending order
       List.Node nextOfMiddle = middle.next;  // Get next node to be compared

       middle.next = null;  //set to null

       List.Node left = naturalMergeSort(h);  //natural Merge sort on left half of list
       List.Node right = naturalMergeSort(nextOfMiddle);  //natural Merge sort on right half of list

       return merge(left, right);  //merge left and right lists- once broken to smallest form

   }// end method mergeSort



    /*
    Partitions the merge sort based on when value in node B is < node a
    Goal: takes advantage of order in the file
     */


    private List.Node getPartition(List.Node head) {

       List.Node result = null;  //initiate result node
        if (head == null) {  //base case return head
            return head;
        }
        List.Node a = head, b = head.next; //a = head node, b = next node

        while (b!= null && b.next!=null) {  //while there is content in the list
            if ((int) a.getItem() <= (int) b.getItem()) { //keep moving if a <=b (i.e. order is ascending)
                a = a.next;
                b = b.next;

            } else  {  //otherwise, end loop
                break;
            }//end else

        }
        result =a;  //set reuslt to a node
        return result; //return result
    }  //end method getPartition


    /**
     *
     * Utility method to print contents of list to screen
     * @param headref
     * @return
     */

   public List.Node printList(List.Node headref) {
        while (headref != null) { //while node is not null
            System.out.print(headref.item.toString() + " ");  //write string to screen + formatting
            headref = headref.next; //onto next node
        }
        return headref;
    }  //end printList method


}//end class NaturalMergeSortLL
