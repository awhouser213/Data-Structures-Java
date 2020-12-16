/**
 * Last-In- First-Out (LIFO) Stack of generic items
 * Operations Push, pop, peek, isEmpty, numbter of items in stack
 *
 * Singly-linked lit with nested class for liked-list nodes
 *
 * @param <Item> - generic type of an item in this stack
 */
public class Stack <Item> {
    private Node top;  // top of stack
    private int count;  // track number of nodes on stack

    // Generic node class.
    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * Initializes an empty stack
     * @return
     */
    Stack() {
        top = null;
        count = 0;
    }

    /**
     * Returns number of items in stack
     * @return
     */
    public int size(){
        return count;
    }

    /**
     * Add an item to stack
     * @param t item to be added
     */

    public void push(Item t) {
        Node tempNode = top;
        top = new Node();
        top.item = t;
        top.next = tempNode;
        count++;
    }

    /**
     * Removes and returns item most recently added to the stack
     *
     * @return
     * @throws NullPointerException
     */
    public Item pop() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException("Stack is Empty.");
        } else {
            Item t = top.item;  //save item to return
            top = top.next;  //delete first node
            count--;  // one less item on stack
            return t;  //return saved item
        }

    }


    // Returns but does not remove the top vertex of the stack

    public Item peek() {
        if (isEmpty()){
            throw new NullPointerException("Stack is Empty.");
        } else{
            return top.item;
        }  //end else
    }  //end peek method

    // Checks if the stack is empty
    //Returns true if stack is empty; false if there are items in the stack

    private boolean isEmpty() {
        return top == null;
    }


    @Override
    public String toString() {
        Node currentNode;
        currentNode = top;

        StringBuffer sb = new StringBuffer();

        while (currentNode != null) {
            sb.append(currentNode.item).append(" | ");
            currentNode = currentNode.next;
        }
        return sb.toString();
    }

}