import java.util.ArrayList;

public class Cell {

    private int val, row, col;
    private Box parent;
    private ArrayList<Integer> options;
    private boolean usedToClear;

    public Cell(int val, int row, int col, Box parent) {
        this.val = val;
        this.row = row;
        this.col = col;
        this.parent = parent;
        if (this.val == 0) {
            options = new ArrayList<Integer>();
            for (int i = 1; i < 10; i++) {
                options.add(i);
            }
        }
        usedToClear = false;
    }

    public boolean getUsedToClear(){
        return usedToClear;
    }

    public int getOptionsSize(){
        return options.size();
    }

    public void fill(){
        val = options.get(0);
        clearOption();
    }


    public int getVal() {
        return val;
    }

    public void removeOption(int option) {
        if(val == 0 && options.indexOf(option) != -1) {
            options.remove(options.indexOf(option));
            if(options.size() == 1){
                fill();
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void clearOption(){
        clearOptionFromRow();
        clearOptionFromCol();
        clearOptionFromBox();
        usedToClear = true;
    }

    public void clearOptionFromRow(){
        for(Cell cell : parent.getParent().getCells()[row]){
            cell.removeOption(val);
        }
    }

    public void clearOptionFromCol(){
        Cell[][] cells = parent.getParent().getCells();
        for (int i = 0; i < cells.length; i++) {
            cells[i][col].removeOption(val);
        }
    }

    public void clearOptionFromBox(){
        for(Cell[] cells : parent.getCells()){
            for(Cell cell : cells){
                cell.removeOption(val);
            }
        }
    }
}
