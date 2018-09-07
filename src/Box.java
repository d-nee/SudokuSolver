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

    public Cell[][] getCells() {
        return cells;
    }
}
