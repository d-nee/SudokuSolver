import java.util.ArrayList;

public class Board {

    private Cell[][] cells;

    public Board(int[][] values){
        ArrayList<Cell>[] boxSets = new ArrayList[9];
        cells = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            boxSets[i] = new ArrayList<Cell>();
        }
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                Cell cell = new Cell(values[i][j], i, j, this);
                cells[i][j] = cell;
                int boxIdx = i/3*3 + j/3;
                boxSets[boxIdx].add(cell);
            }
        }

        for (int i = 0; i < 9; i++) {
            Cell[] box = new Cell[9];
            for (int j = 0; j < 9; j++) {
                box[j] = boxSets[i].get(j);
            }
            new Set(box, "box");
            new Set(cells[i], "row");
            Cell[] list = new Cell[9];
            for (int j = 0; j < 9; j++) {
                list[j] = cells[j][i];
            }
            new Set(list, "col");
        }


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
