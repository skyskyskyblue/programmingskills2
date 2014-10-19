
/**
 * This class creates a map using a 2D array data structure; the assignment specifies that the grid has
 * ny rows and nx columns and each element is referred to by (nx,ny), the rows range from 1 to ny and
 * the columns range from 1 to nx, and that the grid "starts" at the bottom left corner so that the
 * element at column 1 and row 1 is in the bottom left corner. Dry portions of land in the map are coded
 * with a 1 and water areas are coded with a zero.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class GridMap
{
    private int[][] map;
    private int ny; //the number of rows
    private int nx; //the number of columns
    
    /**
     * This constructor takes in a bitmask ASCII file to create a new map.
     * 
     * @param nrow The number of rows for the map.
     * @param ncol The number of columns for the map.
     * @param file The incoming bitmask ASCII file.
     */
    
    public GridMap(int nrow, int ncol,File file)
    {
    }
    
    /**
     * Constructor where a map can be created from a given number of input columns and rows and it
     * then randomly generates a map with those dimensions where (roughly) 25% of the map is water. 
     * 
     * @param nrow The number of rows for the map.
     * @param ncol The number of columns for the map.
     */
    public GridMap(int nrow, int ncol)
    {
        Random rand = new Random();
        
        nx = nrow;
        ny = ncol;
        
        //the map must dimensions must not exceed 2000 x 2000
        if(nx > 2000)
            nx = 2000;
        if(ny > 2000)
            ny = 2000;
        
        map = new int[nx][ny];
        
        /*
         * Creating a map using the random number generator
         */
        
        for(int i = 0; i < map.length; i++) //the i represents the row
        {
            for(int j = 0; j < map[0].length; j++) //the j represents the column
            {
                //randomly assigns a numbers to the map
                if(rand.nextInt(4) == 0) //if the number is 0 then the space becomes water
                    map[i][j] = 0;
                else                    //if the number is 1, 2, or 3 then the space becomes land
                    map[i][j] = 1;
            }
        }
        
    }
    
    /**
     * This method returns the row of the element as it is stored in the 2D array; because the map
     * can't be stored in the program like it is represented in the model the coordinates in the data
     * structure will be different from those in the map.
     * 
     * @param nrow The row of the element in the map.
     */
    
    public int getRow(int nrow)
    {
        return ny - nrow;
    }
    
    /**
     * This method returns the column of the element as it is stored in the 2D array; because the map
     * can't be stored in the program like it is represented in the model the coordinates in the data
     * structure will be different from those in the map.
     * 
     * @param ncol The row of the element in the map.
     */
    public int getCol(int ncol)
    {
        return nx - ncol;
    }
    
    /**
     * This method returns the number of dry squares adjacent to the square at map[row][col]
     * using the square's data coordinates.
     * 
     * @param row The row of the square's data coordinates.
     * @param col The column of the square's data coordinates.
     * 
     * @return The number of dry neighbors
     */
    
    public int getDryNeighbors(int row2, int col2)
    {
        int row = getRow(row2); //gets the row for the element in the map's data structure
        int col = getCol(col2); //gets the column for the element in the map's data structure
        int neighbors = 0; //the number of dry neighbors
        
        /*
         * If the element is in a corner then it can only have two neighbors.
         */
        
        if(row == 0 && col == 0) //if it's in the top left of the map
        {
            if(isDry(0,1))
                neighbors++;
            if(isDry(1,0))
                neighbors++;
        }
        else if(row == (ny - 1) && col == (nx - 1)) //if it's in the bottom right of the map
        {
            if(isDry(ny - 2,nx - 1))
                neighbors++;
            if(isDry(ny - 1,nx - 2))
                neighbors++;
        }
        else if(row == (ny - 1) && col == 0)//if it's in the bottom left of the map
        {
            if(isDry(ny - 2,0))
                neighbors++;
            if(isDry(ny - 1,1))
                neighbors++;
        }
        else if(row == 0 && col == (nx - 1))// if it's in the top right of the map
        {
            if(isDry(0,nx - 2))
                neighbors++;
            if(isDry(1, nx - 1))
                neighbors++;
        }
            
        /*
         * If the element is on the side of the map, but not in the corner then it has only three 
         * neighbors
         */
        
        else if(row == 0) //if it's in the top row of the map
        {
            if(isDry(row,col - 1))
                neighbors++;
            if(isDry(row,col + 1))
                neighbors++;
            if(isDry(row + 1,col))
                neighbors++;
        }
        else if(row == (ny - 1)) //if it's in the bottom row of the map
        {
            if(isDry(row,col - 1))
                neighbors++;
            if(isDry(row,col + 1))
                neighbors++;
            if(isDry(row - 1,col))
                neighbors++;
        }
        else if(col == 0) // if it's on the left side of the map
        {
            if(isDry(row - 1,col))
                neighbors++;
            if(isDry(row + 1,col))
                neighbors++;
            if(isDry(row,col + 1))
                neighbors++;
        }
        else if(col == (nx - 1)) //if it's on the right side of the map
        {
            if(isDry(row - 1,col))
                neighbors++;
            if(isDry(row + 1,col))
                neighbors++;
            if(isDry(row,col - 1))
                neighbors++;
        }
        
        /*
         * Finally for the general case where the square is not in a corner or on one of the sides
         * of the map
         */
        else
        {
            if(isDry(row,col - 1))
                neighbors++;
            if(isDry(row,col + 1))
                neighbors++;
            if(isDry(row - 1,col))
                neighbors++;
            if(isDry(row + 1,col))
                neighbors++;
        }
        
        return neighbors;
    }
    
    /**
     * This method returns true if the square is dry land.
     * 
     * @param row The row of the square in terms of map coordinates.
     * @param col The column of the square in terms of map coordinates.
     * 
     * @return True is the neighbor is dry. 
     */
    
    public boolean isDry(int col, int row)
    {
        if(map[getRow(row)][getCol(col)] == 1)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    /**
     * This method prints out a graphical representation of the map. 
     * 
     * @return Graphic representation of the map.
     */
    
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[0].length; j++)
            {
                string.append(map[i][j] + " ");     //adds the number to the StringBuilder
            }
            
            string.append("\n"); //starts a new line for the next row
        }
        
        return new String(string);
    }
    
    /**
     * This method returns the number of columns in the map.
     * 
     * @return The number of columns in the map.
     */
    
    public int getNCols()
    {
        return nx;
    }
    
    /**
     * This method returns the number of rows in the map.
     * 
     * @return The number of rows in the map.
     */
    
    public int getNRows()
    {
        return ny;
    }
}
