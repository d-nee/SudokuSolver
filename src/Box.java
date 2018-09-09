import java.lang.reflect.Array;
import java.util.ArrayList;

public class Box {

    private Cell[][] cells;
    private int row, col;
    private Board parent;
    public Box(int r, int c, Board parent){
        cells = new Cell[3][3];
        row = r;
        col = c;
        this.parent = parent;
    }

    public void addCell(int val, int r, int c){
        Cell cell = new Cell(val, r + 3 * row, c + 3 * col, this);
        cells[r][c] = cell;
        parent.addCell(cell);

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


    public Cell[][] getCells() {
        return cells;
    }

    public Board getParent() {
        return parent;
    }
}
