
/**
 * Linked- List to track path options in adjacency matrix
 *
 * @param <T>
 */

public class List<T> {

    private Node head;  // first node in list
    private Node tail;  // last node in list
    private int count;  // track number of nodes in the list

    private class Node {
        private T item;  // content in node of generic type argument T
        private Node prev;  // pointer to previous node
        private Node next;  // pointer to next node
    }

    /**
     *Initalize an empty linked list
     *
     */
    List() {
        head = null;  //empty list
        tail = null;  //empty list
        count = 0;    //empty list
    }

    /**
     * Getter for size
     * @return count- size of list
     */
    public int getCount() {
        return count;
    }

    /**
     * Getter for head node
     * @return head
     */
    public Node getHead() {
        return head;
    }

    /**
     * Getter for tail node
     * @return tail
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Chek if list is empty (true) or not (false)
     * @return boolean true/false if list is empty
     */

    private boolean isEmpty() {
        return count == 0;
    }

    /**
     * Add a node to end of the list.
     * Increase list size
     */
    public void append(T t) {
        if (isEmpty()) {                    //For an empty list we will need to initialize the list, setting the first node as the head and tail node
            Node tempNode = new Node();
            tempNode.item = t;
            tempNode.next = null;
            tempNode.prev = null;
            head = tempNode;
            tail = tempNode;
            count++; //increase count of nodes

        } else {                              //existing list: add new node to tail, using a temp node as intermediary
            Node tempNode = new Node();
            tempNode.item = t;
            tempNode.next = null;
            tail.next = tempNode;
            tempNode.prev = tail;
            tail = tempNode;
            count++;  //increase count of nodes
        }

    }

    /**
     * Remove node from end of the list.
     * Decrease list size
     */
    public void removeLast() {
        if (isEmpty()) {  //cannot remove node from an empty list, return error message
            throw new NullPointerException("Empty List!");
        } else if (head == tail) {  // 1 item in list, remove that item, reset list to null
            head = null;
            tail = null;
        } else {  //If there are more than 1 items in the list, remove the last node
            tail = tail.prev;
            tail.next.prev = null;
            tail.next = null;
            count--;  //1 less item, decrement count
        }

    }

    /**
     * Make a deep copy of the list.
     */
    public List<T> copyList() {  //create a copy of the list
        List<T> tempList = new List<T>();  //initiate new list temPaaaa
        Node currentNode = head;  //make first node the head

        while (currentNode != null) {  //while node is not null
            tempList.append(currentNode.item);  //add item from currentnode to new list
            currentNode = currentNode.next; //mode to next node
        }
        return tempList;  //return tempList
    }


    /**
     * Method to convert list to string output
     * @return String sb- list as a string value
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();  // create new string buffer
        Node currentNode = head;  //set current node as head node

        if (isEmpty()) {
            return sb.append("No Path in Graph").toString();  //no path exists
        }//end if
        else {
            while (currentNode != null && currentNode.item != null) {  //run as long as there are values in the list
                if (currentNode.item instanceof List) {  //check for Node same type as List
                    sb.append(currentNode.item.toString());  //append item to string
                    sb.append("\r\n");  //formatting
                }//end if
                else {
                    sb.append(currentNode.item);
                    if (currentNode != tail) {  //i.e. more paths exist
                        sb.append(" -> ");  //formatting- add arrow between nodes on path
                    } //end if
                }  //end else
                currentNode = currentNode.next;  //move to next node
            }  //end while loop
        } //end else
        return sb.toString().trim();  //return string value of list, trimmed
    }  //end method toString
}