import java.lang.reflect.Array;
import java.util.ArrayList;

public class Box {

    private Cell[][] cells;
    private int row, col;
    public Box(int r, int c){
        cells = new Cell[3][3];
        row = r;
        col = c;
    }

    public void addCell(int val, int r, int c){
        cells[r][c] = new Cell(val, r + 3 * row, c + 3 * col, this);
    }

    public boolean isFilled(){
        for(Cell[] cellRow : cells){
            for(Cell cell: cellRow){
                if(cell.getVal() == 0) {
                    return false;
                }
            }
        }
    return true;
    }

    public void clearOptionFromRow(int r, int option){
        for(Cell cell: cells[r]){
            if(cell.getVal() == 0) {
                cell.removeOption(option);
            }
        }
    }

    public void clearOptionFromColumn(int c, int option){
        for(int i = 0; i < 3; i++){
            if(cells[i][c].getVal() == 0) {
                cells[i][c].removeOption(option);
            }
        }

    }
    public void clearOptions(){
        ArrayList<Integer> toBeCleared = new ArrayList<Integer>();
        for(Cell[] cellRow: cells){
            for(Cell cell: cellRow){
                if(cell.getVal() != 0 && !cell.getUsedToClear()){
                    toBeCleared.add(cell.getVal());
                }
            }
        }
        for(Cell[] cellRow: cells){
            for(Cell cell: cellRow){
                if(cell.getVal() == 0){
                    for(int a : toBeCleared){
                        cell.removeOption(a);
                    }
                }
            }
        }


    }
    public void useBox(){
        for(Cell[] cellRow: cells){
            for(Cell cell: cellRow){
                if(cell.getVal() != 0){
                    cell.use();
                }
            }
        }

    }
    public void fillNakedSingles(){
        for(Cell[] cellRow: cells){
            for(Cell cell: cellRow){
                if(cell.getVal() == 0 && cell.getOptionsSize() == 0){
                    cell.fill();
                }
            }
        }
    }


    public Cell[][] getCells() {
        return cells;
    }

//Need long name because I'm tracking which cells have been used to reduce the amount of work the computer is doing
    public ArrayList<Integer> getNewOptionsFromRow(int r){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int c = 0; c < 3; c++){
            if(!cells[r][c].getUsedToClear() && cells[r][c].getVal() != 0) {
                result.add(cells[r][c].getVal());
            }
        }
        return result;
    }
    public ArrayList<Integer> getNewOptionsFromColumn(int c){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int r = 0; r < 3; r++){
            if(!cells[r][c].getUsedToClear() && cells[r][c].getVal() != 0) {
                result.add(cells[r][c].getVal());
            }
        }
        return result;
    }


}
