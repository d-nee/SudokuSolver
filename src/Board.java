import java.util.ArrayList;

public class Board {

    private Box[][] boxes;
    private Cell[][] cells;

    public Board(int[][] values){
        boxes = new Box[3][3];
        cells = new Cell[9][9];
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                boxes[i][j] = new Box(i, j, this);
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

    public void addCell(Cell cell){
        cells[cell.getRow()][cell.getCol()] = cell;
    }

    public boolean isSolved(){
        for(Cell[] row : cells){
            for(Cell cell : row){
                if(cell.getVal() == 0){
                    return false;
                }
            }
        }
        return true;
    }
    public void fillNakedSingles(){
//        clearRowOptions();
//        clearColumnOptions();
//        clearBoxOptions();
        for(Cell[] row : cells){
            for(Cell cell : row){
                if(cell.getVal() != 0){
                    cell.clearOption();
                }
            }
        }

    }

    public void printBoard(){
        for(Cell[] row : cells){
            for(Cell cell : row){
                System.out.print(cell.getVal() + " ");
            }
            System.out.println();
        }
    }


    public Cell[][] getCells() {
        return cells;
    }
}
