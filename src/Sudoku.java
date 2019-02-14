
import java.util.Random;
import java.util.ArrayList;

public class Sudoku {

    Integer boardArray[][] = new Integer[9][9];
    int tryCounter = 0;

    /*
        function that fills a 9x9 sudoku board with integers from 1-9

        Arguments:
            int Row (index of row)
            int Col (index of column)
    */
    boolean fillBoard(int row, int col){

        tryCounter = 0;

        //base case (if index is passed [8][8] -> sudoku is filled
        if (row == 9 && col == 9){
            return true;
        }

        int number;
        int tempNumber;



        //if column is in the last position, increment to the next row
        if (col == 9){

            // Increments row
            if (row < 9) {
                col = 0;
                return fillBoard(row+1, col);
            }
            else {
                return true;
            }
        }

        number = getRandNumber(); //random number generated from 1-9
        tempNumber = number; //store number as a temporary integer


        if (row < 9){ //start integers filling per row

            //checks if an integer can be placed, considering the rules for Sudokus
            while(!legalPlacement(number, row, col)){
                number = newNumber(tempNumber); //if wrong number, increment it following the spec
                tempNumber = number;
                tryCounter++;


                //in case no number can be generated to match, reset the row and try again.
                if (tryCounter == 9) {
                    for (int i = col; i > 0; i--){
                        boardArray[row][col-i]= 0; //empties row
                    }


                    tryCounter++;

                    //start from first column again
                    return fillBoard(row, 0);

                }
            }

            //places integer in the sudoku
            if (col != 9){
                placeNumber(number, row, col);
                return fillBoard(row, col+1);

            }
        }


        return true;
    }

    /*
        function that places number in correct spot in the grid

        Arguments:
            int number (integer to be placed in grid)
            int row (index of row)
            int col (index of column
    */
    void placeNumber(int number, int row, int col){
        boardArray[row][col] = number;
    }

     /*
        function to check if an integer can be placed

        Arguments:
            int number (integer to be placed in grid)
            int row (index of row)
            int col (index of column
    */
    boolean legalPlacement(int number, int row, int col){


        //vertical
        if (row != 0 ){ //check rows before the current one
            for (int i = 1; i <= row; i++){ //counts how many rows are above current row
                if (boardArray[row-i][col].equals(number)){ //checks if number appears in the row above
                    return false;
                }
            }
        }


        //horizontal
        if (col != 0){ //check columns before the current one
            for (int i = 1; i <= col;i++){
                if (boardArray[row][col-i].equals(number)){
                    return false;
                }
            }
        }

        //make sure index of row can not go negative
        int tempRow = 2;
        if (row == 1) {
            tempRow = row;
        }




        //below statements are to check the 3x3 blocks that have to follow Sudoku guidelines as well

        if (0 < row) { // Check if we are not on first row



            //Block 1, Column 1
            if (0 <= col && col <= 2) {
                // Increment rows through the block
                    // Block one in column
                    if (row <= 2) {
                        for(int i = 0; i <= tempRow; i++) {
                            if (boardArray[row - i][0].equals(number) || boardArray[row - i][1].equals(number) || boardArray[row - i][2].equals(number)) {
                                return false;
                            }
                        }
                    }
                    // Block 2, Column 1
                    if (3 <= row && row <= 5) {
                        for(int i = 0; i <= (row-3); i++) {
                            if (boardArray[row - i][0].equals(number) || boardArray[row - i][1].equals(number) || boardArray[row - i][2].equals(number)) {

                                return false;
                            }
                        }
                    }
                    // Block 3 in Column 1
                    if (6 <= row && row <= 8) {
                        for(int i = 0; i <= (row-6); i++) {
                            if (boardArray[row - i][0].equals(number) || boardArray[row - i][1].equals(number) || boardArray[row - i][2].equals(number)) {
                                return false;
                            }
                        }
                    }
            }




            //second row of blocks

            //Block 1 in Column 2
            if (3 <= col && col <= 5) {
                // Increment rows through the block
                // Block one in column
                if (row <= 2) {
                    for(int i = 0; i <= tempRow; i++) {
                        if (boardArray[row - i][3].equals(number) || boardArray[row - i][4].equals(number) || boardArray[row - i][5].equals(number)) {
                            return false;
                        }
                    }
                }
                // Block 2 in Column 2
                if (3 <= row && row <= 5) {
                    for(int i = 0; i <= (row-3); i++) {
                        if (boardArray[row - i][3].equals(number) || boardArray[row - i][4].equals(number) || boardArray[row - i][5].equals(number)) {
                            return false;
                        }
                    }
                }
                // Block 3 in Column 2
                if (6 <= row && row <= 8) {
                    for(int i = 0; i <= (row-6); i++) {
                        // Now we check the whole block
                        if (boardArray[row - i][3].equals(number) || boardArray[row - i][4].equals(number) || boardArray[row - i][5].equals(number)) {
                            return false;
                        }
                    }
                }
            }




            //third row of blocks

            //Block 1 in Column 3
            if (6 <= col && col <= 8) {
                // Increment rows through the block
                // Block one in column
                if (row <= 2) {
                    for(int i = 0; i <= tempRow; i++) {
                        if (boardArray[row - i][6].equals(number) || boardArray[row - i][7].equals(number) || boardArray[row - i][8].equals(number)) {
                            return false;
                        }
                    }
                }
                // Block 2 in Column 3
                if (3 <= row && row <= 5) {
                    for(int i = 0; i <= (row-3); i++) {
                        if (boardArray[row - i][6].equals(number) || boardArray[row - i][7].equals(number) || boardArray[row - i][8].equals(number)) {
                            return false;
                        }
                    }
                }
                // Block 3 in Column 3
                if (6 <= row && row <= 8) {
                    for(int i = 0; i <= (row-6); i++) {
                        if (boardArray[row - i][6].equals(number) || boardArray[row - i][7].equals(number) || boardArray[row - i][8].equals(number)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

     /*
        function to create new number based on random number

        Arguments:
            int tempNumber (random number passed through from rand function)
    */

    int newNumber(int tempNumber){


        if (tempNumber == 9){ //if int == 9, should increment to 1
            return 1;
        }

        return (tempNumber+1); //increment +1
    }


     /*
        function to generate random number for each cell
    */

    int getRandNumber(){

        Random rand = new Random();
        int low = 1;
        int high = 10;

        int candidate;

        candidate = rand.nextInt(high-low) + low;


        return candidate;
    }






     /*
        function to print out sudoku in 3x3 grids including dividers

        Arguments:
            Integer[][] sudoku (2d array of Integers)
    */

    void printBoard(Integer[][] sudoku){


        for(int i = 0; i < 9; i++)
        {
            if (i % 3 == 0){ //every 4th index it needs a divider
                System.out.println("  - - - - - - - - - - -");
            }

            for(int j = 0; j < 9; j++)
            {
                if (j % 3 == 0){ //every 4th index it needs a divider
                    System.out.print("| ");
                }

                System.out.printf("%1d ", sudoku[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("  - - - - - - - - - - -");

    }

    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku();


        sudoku.boardArray =
                new Integer[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}
                };

        sudoku.fillBoard(0,0);
        sudoku.printBoard(sudoku.boardArray);

    }


}
