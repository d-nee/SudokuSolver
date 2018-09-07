
public class Cell {

    private int val, row, col;
    private Box parent;
    public Cell(int val, int row, int col, Box parent){
        this.val = val;
        this.row = row;
        this.col = col;
        this.parent = parent;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
