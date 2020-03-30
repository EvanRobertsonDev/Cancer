/*
 * Evan Robertson
 * Cancer.java
 * March 30th 2020
 * This program reads a pre-written file that contains a 15x15 grid of + and - symbols
 * Each - represents a cancer cell and the program erases all cancer cells and replaces
 * it with empty space. It then returns a fixed graph along with how many cancer spots were
 * found.
 */

package cancer;
import java.io.*;

/**
 *
 * @author Evan
 */
public class Cancer {
    
    //Global variables
    public static char grid[][];
    public static int blobs = 0;
    public static int cells = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Variables to determine position on grid
        int row, col = 0;
        
        //The grid: a 15x15 that imports from file grid.properties
        grid = new char[17][17];
        //Create a buffered reader
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(new File("src/Cancer/grid.properties")));
            System.out.println("Recieving file data...");
            System.out.println("File found!");
        } catch(FileNotFoundException e){
            System.err.println(e);
        }
       
        //Transfer file data to array
        for (row = 0; row < 16; row++) {
            for(col = 0; col < 16; col++) {
                try {
                    grid[row][col] = (char)buffer.read();
                } catch(IOException e) {
                    System.err.println(e);
                }
            }
        }
        
        System.out.println("Original grid:");
        //Print untouched grid from array
        outputGrid();
        
        //Nested loops to search each area of the grid
        for (row = 0; row < 16; row++) {
            for(col = 0; col < 16; col++) {
                //Set the amount of cells per blob of cancer to zero
                cells = 0;
                //Call the method to delete all cancer
                blobFinder(row, col);
                //If there was any cancer found in the scan, increase the blobs size
                if(cells > 0) {
                    blobs++;
                }
            }
        }
        //Output amount of blobs and rewrite the grid without any cancer
        System.out.println("There are " + blobs + " spots of cancer cells on file");
        System.out.println("Cleansing file of Cancer cells...");
        outputGrid();
    }
    //This method finds cancer, detects any cancer surrounding it and replace it all with empty space
    public static void blobFinder(int row, int col) {
        if (grid[row][col] == '-') {
            //Replace with a space
            grid[row][col] = ' ';
            //Check each possible surrounding object
            blobFinder(row - 1, col - 1);
            blobFinder(row - 1, col);
            blobFinder(row - 1, col + 1);
            blobFinder(row, col - 1);
            blobFinder(row, col + 1);
            blobFinder(row + 1, col - 1);
            blobFinder(row + 1, col);
            blobFinder(row + 1, col + 1);
            //Increase cell size if a cancer cell is found
            cells++;
        }
        
    }
    //This method outputs the grid as if it were straight from the file
    public static void outputGrid() {
            String output = "";
                //Add each element of the array to a string
                for (int i = 0; i < 16; i++) {
                    for(int k = 0; k < 16; k++) {
                        output = output + grid[i][k];
                }
            }
            //Print
            System.out.println(output);
        }
    }
    
