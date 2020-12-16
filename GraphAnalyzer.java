import java.io.IOException;

/**
 *
 * Class to organize information and provide formatting to output file
 *
 * Methods
 * Write- master method, calls on below methods
 * linearSearch- search through the entire path list and selects the paths with the corresponding start and end vertices
 * addLine- add a dashed line for formatting (X)
 * matrixToString- converts the matrix to string;  represented in output file before
 */

public class GraphAnalyzer {
    private boolean[][] adjMatrix;
    private String paths;
    private MyFileWriter outFile;
    private int[][] headerFormat;
    private String[] headers;
    private static int counter = 1;

    /**
     * Generic constructor to accept 3 arguments: adjacency matrix, Paths string, and output file
     *
     * @param adjMatrix
     * @param paths
     * @param outFile
     */
    GraphAnalyzer(boolean[][] adjMatrix, String paths, MyFileWriter outFile) {
        this.adjMatrix = adjMatrix;
        this.paths = paths;
        this.outFile = outFile;
        headers = new String[adjMatrix.length * adjMatrix.length];  //set headers size as adjMatrix size *2
        createHeaderFormat();
        createPathHeader();
    }

    /**
     * Main method in GraphAnalyzer class
     * Write to file:
     *      Matrix- matrixToString method
     *      Formatting- addLine method
     *      Headers- createPathHeader() + createHeaderFormat() methods
     * Calls on linerSearch for paths options

     * @throws IOException
     */

    public void write() throws IOException {
        StringBuffer sb = new StringBuffer();  //create new string buffer
        sb .append(addLine());  //format
        sb .append("Matrix " + counter + "\r\n"); //Matrix number
        counter++;  //add to counter
        sb .append(addLine());  //format: add line
        sb .append(matrixToString());  //Display the adjacency matrix as a string
        sb .append(addLine()); //format: add line

        for (int row = 0; row < headers.length; row++) {  // iterate through each row
            sb .append(headers[row]);  //add header
            sb .append(linearSearch(Integer.toString(headerFormat[row][0]), Integer.toString(headerFormat[row][1]))); //call linear search method to find possible paths
            sb .append(addLine());  //format: add line
        }
        sb .append("\r\n");  //format: new line
        outFile.writeToFile(sb .toString());  //write string buffer out to file
    }//end write method

    /**
     * Method to search through lists (containing paths), and select the paths with corresponding start and end vertices
     * @param startVertex
     * @param endVertex
     * @return StringBuffer sb
     */

    private String linearSearch(String startVertex, String endVertex) {
        StringBuffer sb = new StringBuffer();  //initiate new string buffer
        boolean pathExists = false;  //boolean if path exists, defaulted to false

        String[] splitPaths = paths.split("\\s*\\r?\\r\n\\s*");  // Split the paths string into its respective lines.
        for (String s : splitPaths) {
            if (s.startsWith(startVertex) && s.endsWith(endVertex)) {  //for corresponding start and end vertices
                sb.append(s).append("\r\n");  //add to string buffer + formating
                pathExists = true;  //set pathfound to true
            }  //end if
        }  // end for

        if (!pathExists) {
            sb.append("No Path Exists \r\n");  //No path found output
        } //end if

        return sb.toString();  //return String Buffer

    }

    /**
     * Method to add a line for separation/ formatting
     * @return String "---- \r\n"
     */

    private String addLine() {
        return "------------------------------------\r\n";
    }  //end method addLine

    // Converts the 2D boolean adjacency matrix to a String for streaming.

    /**
     * Method to convert adjacency matrix to a string representation
     * Used before showing the paths in Output File
     *
     * @return String sb matrix as a String value
     */

    private String matrixToString() {
        StringBuffer sb = new StringBuffer();  //initialize a new string buffer

        for (int row = 0; row < adjMatrix.length; row++) {  //for each row and column
            for (int col = 0; col < adjMatrix[row].length; col++) {
                if (adjMatrix[row][col]) {  //If there is a 1
                    sb.append("1").append(" ");  //append a 1 + space
                } else {  //if there is a 0
                    sb.append("0").append(" ");  //append a 0 + space
                }
            }
            sb.append("\r\n");  //new line
        }

        return sb.toString();  //return output
    }

    /**
     * Method to add values to path header
     */

    private void createPathHeader() {
        String pathHeader = "Path From %d to %d: \r\n";  // initiate string for path header

        for (int i = 0; i < headerFormat.length; i++) {  //for each row
            headers[i] = String.format(pathHeader , headerFormat[i][0], headerFormat[i][1]);  //add pathHeader to each row
        }  //end for loop
    }//end createPathHeader

    /**
     * Method to create all possible node combinations in a graph, regardless of if there are existing paths
     */

    private void createHeaderFormat() {
        int numHeaders = adjMatrix.length;  //create variable number of headers as length of adjacency matrix
        int n = numHeaders * numHeaders;  //variable to track size of matrix
        headerFormat= new int[n][2];
        int numRows = 0;  //track number of rows

        for (int row = 0; row < numHeaders; row++) {  //for each row and column
            for (int col = 0; col < numHeaders; col++) {
                headerFormat[numRows][0] = row;  //set rows
                headerFormat[numRows][1] = col;  //set columns
                numRows++;
            } //end for
        } //end for
    } //end createHeaderFormat



}  //end Graph Analyzer
