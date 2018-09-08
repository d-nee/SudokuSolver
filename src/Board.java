import java.util.ArrayList;

public class Board {

    private Box[][] boxes;

    public Board(int[][] values){
        boxes = new Box[3][3];
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                boxes[i][j] = new Box(i, j);
            }
        }
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                int boxRow = i/3;
                int boxCol = j/3;
                boxes[boxRow][boxCol].addCell(values[i][j], i - 3*boxRow, j - 3*boxCol);
            }
        }
    }

    public boolean isSolved(){
        for (Box[] boxRow: boxes) {
            for(Box box: boxRow){
                if(!box.isFilled()){
                    return false;
                }
            }

        }
       return true;
    }
    public void fillNakedSingles(){
        clearRowOptions();
        clearColumnOptions();
        clearBoxOptions();
        for (Box[] boxRow: boxes) {
            for(Box box: boxRow){
                box.useBox();
                box.fillNakedSingles();
            }

        }

    }

    public void clearBoxOptions(){
        for (Box[] boxRow: boxes) {
            for(Box box: boxRow){
                box.clearOptions();
            }

        }
    }

    public void clearRowOptions(){
        ArrayList<Integer> toBeCleared;
        for(int boxRow = 0; boxRow < boxes.length; boxRow++){
            for(int cellRow = 0; cellRow < 3; cellRow ++) {
                toBeCleared = getRowOptions(boxRow,cellRow);
                for (int boxCol = 0; boxCol < boxes[0].length; boxCol++) {
                    for(int a: toBeCleared) {
                        boxes[boxRow][boxCol].clearOptionFromRow(cellRow, a);
                    }
                }
                toBeCleared.clear();
            }
        }
    }

    public void clearColumnOptions(){
        ArrayList<Integer> toBeCleared;
        for(int boxCol = 0; boxCol < boxes.length; boxCol++){
            for(int cellCol = 0; cellCol < 3; cellCol ++) {
                toBeCleared = getColumnOptions(boxCol,cellCol);
                for (int boxRow = 0; boxRow < boxes[0].length; boxRow++) {
                    for(int a: toBeCleared) {
                        boxes[boxRow][boxCol].clearOptionFromColumn(cellCol, a);
                    }
                }
                toBeCleared.clear();
            }
        }
    }
    //returns options needed to be cleared from a given row/column.
    public ArrayList<Integer> getRowOptions(int boxRow, int cellRow){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Box box: boxes[boxRow]) {
            result.addAll(box.getNewOptionsFromRow(cellRow));
        }
        return result;
    }
    public ArrayList<Integer> getColumnOptions(int boxCol, int cellCol){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int r  = 0; r < 3; r++){
            result.addAll(boxes[r][boxCol].getNewOptionsFromColumn(cellCol));
        }
        return result;
    }




    public Box[][] getBoxes() {
        return boxes;
    }

}
