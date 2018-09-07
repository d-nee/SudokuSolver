import java.util.ArrayList;
import java.util.Arrays;

public class Box {
    private Cell[][] cells;
    private int boxRow, boxColumn;

    public Box(int r, int c, int[] cellInts){
        boxRow = r;
        boxColumn = c;
        cells = new Cell[3][3];
        //sorry if you find this method barbaric
        int cellIntsIndex = 0;
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; i++) {
                cells[r][j] = new Cell(r + 3*boxRow, c + 3*boxRow, cellInts[cellIntsIndex]);
                cellIntsIndex++;
            }
        }
    }
    public void printBox(){
        System.out.println(Arrays.deepToString(getValues2D()));
    }

    public int getBoxRow() {
        return boxRow;
    }

    public int getBoxColumn() {
        return boxColumn;
    }
    public int[][] getValues2D(){
        int[][] values = new int[3][3];
        for(int r = 0; r < cells.length; r++){
            for(int c = 0; c < cells[0].length; c++){
                values[r][c] = cells[r][c].getValue();
            }
        }
        return values;
    }
    public int[] getValues1D(){
        int[] values = new int[9];
        int indexOfValues = 0;
        for(int r = 0; r < cells.length; r++){
            for(int c = 0; c < cells[0].length; c++){
                values[indexOfValues] = cells[r][c].getValue();
                indexOfValues++;
            }
        }
        return values;
    }
    //I suspect we will need these unless we want spaghetti in Board
    public int[] getRowValues(int row){
        //row is tentative, but I think its optimal given our whole "arrays start at 0"
        return getValues2D()[row];

    }
    public int[] getColumnValues(int column){
        //I don't want to code a transpose (NOT WORTH), so I'm gonna hard code it.
        int[][] box = getValues2D();
        int[] values = new int[3];
            values[0] = box[0][column];
            values[1] = box[1][column];
            values[2] = box[2][column];
            return values;

    }
    public void removeCandidate(int candidate){
        for(int r = 0; r < cells.length; r++){
            for(int c = 0; c < cells[0].length; c++){
                cells[r][c].removeCandidate(candidate);
            }
        }
    }

    //Clear candidates that already exist within this box.
    public void clearCandidates(){
        int[] candidates = getValues1D();
        for(int r = 0; r < cells.length; r++){
            for(int c = 0; c < cells[0].length; c++){
                if(cells[r][c].getValue() == 0){
                    //Some cells will not have their arrayList initialized hence the if statement
                    for(int i = 0; i < candidates.length; i++) {
                        cells[r][c].removeCandidate(candidates[i]);
                    }
                }
            }
        }

    }
    //Used for checking if the board is solved
    public boolean isFilled(){
        for(int r = 0; r < cells.length; r++){
            for(int c = 0; c < cells[0].length; c++){
                if(cells[r][c].getValue() == 0){
                    return false;
                }
            }
        }
        return true;
    }

}
