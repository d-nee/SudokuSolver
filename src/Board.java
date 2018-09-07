import java.util.Arrays;

public class Board {
    private Box[][] boxes;

    public Board(int[][] puzzle) {
        /*
        Strategy for splitting the 9x9 into 9 3x3's
        Step 1: Slice into 3 3x9 arrays (easy)
        Step 2: Transpose each 3x9 (basically flip it on its side so it goes from 3x9 to 9x3)
        Step 3: Slice the now 9x3 into 3 3x3's. 4D ARRAY LETS GO BOYS (essentially our project though)
        Step 4: Transpose the 3x3's again to rotate them back to correct values.
        Step 5: Turn the 3x3's into 1x9s. (Yes we end with a 9x9)
        Step 6: Make the 2d box array, as the constructor takes 1x9s for each box.
        */
        int[][][] initialSlice;
        initialSlice = slice(puzzle);
        int[][][][] transposedAndSliced = new int[3][3][3][3];
        for (int i = 0; i < 3; i++) {
            transposedAndSliced[i] = slice(transpose(initialSlice[i]));
        }
        int[][][][] transposedBack = new int[3][3][3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                transposedBack[r][c] = transpose(transposedAndSliced[r][c]);
            }
        }
        int[][] readytoBox = new int[9][9];
        int indexOfReadytoBox = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                readytoBox[indexOfReadytoBox] = twoDto1D(transposedBack[x][y]);
                indexOfReadytoBox++;
            }
        }
        //Finally!
        indexOfReadytoBox = 0;
        boxes = new Box[3][3];
        for(int a = 0; a < boxes.length; a++){
            for(int b = 0; b < boxes[0].length; b++){
                boxes[a][b] = new Box(a,b,readytoBox[indexOfReadytoBox]);
                indexOfReadytoBox++;
            }

        }



    }
    private int[][] transpose(int[][] toBeTransposed){
        int i = toBeTransposed.length;
        //assume all columns are the same size
        int j = toBeTransposed[0].length;
        int[][] transposed = new int[j][i];
        for(int x = 0; x < i; x++){
            for(int y = 0; y < j; j++){
                transposed[y][x] = toBeTransposed[x][y];
            }
        }
        return transposed;

    }

    private int[][][] slice(int[][] toBeSliced){
        int rows = toBeSliced.length;
        int columns = toBeSliced[0].length;
        int[][][] sliced = new int[rows/3][rows/3][columns];
        for(int x = 0; x < rows/3 ; x++){
            sliced[x] = Arrays.copyOfRange(toBeSliced, 3*x, 3*(x+1));
        }
        return sliced;
    }

    private int[] twoDto1D(int[][] twoD){
        //left to right, then top to bottom
        int[] oneD = new int[twoD.length*twoD[0].length];
        int indexOfOneD = 0;
        for(int r = 0; r < twoD.length; r++){
            for(int c = 0; c < twoD[0].length; c++){
                oneD[indexOfOneD] = twoD[r][c];
                indexOfOneD++;
            }
        }
        return oneD;
    }

    //overarching function to check and remove candidates
    public void removeCandidates(){


    }
    //secondary functions necessary for candidate removal
    public void clearRowOfCandidate(int row, int candidate){
        //probably use getRowValues from the box class

    }
    public void clearColumnofCandidate(int column, int candidate){
        //probably use getColumnValues from the box class

    }
    //box candidate removal will happen on the box level (see the clearCandidates method in the box class.

    public boolean isSolved(){
        //double for loop with each box checking if its cells have a value of 0. If all are nonzero, return true.
        for(int r = 0; r < boxes.length; r++){
            for(int c = 0; c < boxes[0].length; c++){
                if(!boxes[r][c].isFilled()){
                    return false;
                }
            }
        }
        return true;
    }
    public void printBoard(){
        for(int r = 0; r < boxes.length; r++){
            for(int c = 0; c < boxes[0].length; c++){
                boxes[r][c].printBox();
            }
            System.out.println();
        }
    }



}
