/**
 * Class to represent a Graph given input adjacency matrix
 * Calls on Stack and list classes
 *
 */

public class Graph {

    private int numVertex;  //number of vertices in graph
    private boolean[][] adjMatrix;  //adjacency matrix of graph representation
    private Stack<Integer> Stack;  //create stack object
    private List<List<Integer>> List;  //create list object
    private boolean[] visitedVertices;  // matrix for visited vertices

    /**
     * Generic constructor for Graph
     * @param matrix
     */
    Graph(boolean [][] matrix) {
        numVertex = matrix.length;
        adjMatrix = matrix;
        Stack = new Stack<Integer>();
        visitedVertices = new boolean[numVertex];
        List = new List<List<Integer>>();
    }

    /**
     * Getter method to return list of paths
     * @return List
     */
    public List<List<Integer>> getList() {
        return List;
    }

    /**
     * Getter method to return number of vertices in the graph
     * @return numVertex
     */
    public int getNumVertex() {
        return numVertex;
    }

    /**
     * Getter method to return adjacency matrix utilized to make graph
     * @return adjMatrix
     */
    public boolean [][] getAdjMatrix() {
        return adjMatrix;
    }


    public void dfsTraverse(int i) {
        List<Integer> path = new List<Integer>();
        path.append(i);
        dfsTraverse(i, path, i);
    }

    /**
     * Private method
     * Recursively traverse graph
     * Depth-first search to find path possibilities
     * @param vertex
     * @param path
     * @param currentPath
     */
    private void dfsTraverse(int vertex, List<Integer> path, int currentPath) {
        Stack.push(vertex);  //add vertex to stack
        visitedVertices[vertex] = true;  //set visited to true

        for (int i = 0; i < numVertex; i++) {
            if (i == currentPath && adjMatrix[vertex][i]) { // Node has a path to itself
                visitedCode(path, i);  //pass to visitedCode method
                path.removeLast();  //remove last item from path
                continue; // force loop to continue for this case
            }//end if
            if (adjMatrix[vertex][i] && !visitedVertices[i]) {  //path exists between nodes
                visitedCode(path, i);
                dfsTraverse(i, path, currentPath);  // Recursive call- check again
            }//end if
        }//end for
        Stack.pop();  //pop stack
        path.removeLast();  //remove last node from path
        visitedVertices[vertex] = false;  //reset visited boolean
    }  //end dfsTraverse



    /**
     * Method for private dfsTraverse() method
     * @param path
     * @param visiting
     */

    private void visitedCode(List<Integer> path, int visiting) {
        List<Integer> tempPath;    //create generic list tempPath
        path.append(visiting);  //add visiting to path
        tempPath = path.copyList();  //copy path to tempPath
        List.append(tempPath);  //append tempPath to list
    }//end visitedCode

}//end class Graph