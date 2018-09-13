import java.util.ArrayList;

public class Board {

    private Cell[][] cells;
    private Set[] rows, cols, boxes;

    public Board(int[][] values){
        ArrayList<Cell>[] boxSets = new ArrayList[9];
        cells = new Cell[9][9];
        rows = new Set[9];
        cols = new Set[9];
        boxes = new Set[9];
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
            boxes[i] = new Set(box, "box");
            rows[i] = new Set(cells[i], "row");
            Cell[] list = new Cell[9];
            for (int j = 0; j < 9; j++) {
                list[j] = cells[j][i];
            }
            cols[i] = new Set(list, "col");
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
    public void fill(){
//        clearRowOptions();
//        clearColumnOptions();
//        clearBoxOptions();
//        for(Cell[] row : cells){
//            for(Cell cell : row){
//                if(cell.getVal() != 0){
//                    cell.clearOption();
//                }
//            }
//        }
        if(!isSolved()) {
            if (cells[0][0].getVal() == 0) {
                guess(cells[0][0]);
            } else {
                guess(getNextCell(cells[0][0]));
            }
        }

    }

    public void guess(Cell c){
        for(Integer o : c.getOptions()) {
            if(isSolved() && checkRules()){
                break;
            }
            c.setValClean(o);
//            System.out.println();
//            printBoard();
            if (checkRules() & !isSolved()) {
                guess(getNextCell(c));
            }
        }
        if(!isSolved()){
            c.setValClean(0);
        }


    }
    public Cell getNextCell(Cell c){
        for(int i = c.getRow(); i < 9; i++) {
            for(int j = 0; j < 9; j++){
                if(i == c.getRow() && j <= c.getCol()){
                }else{
                    if(cells[i][j].getVal() == 0){
                        return cells[i][j];
                    }
                }

            }
        }
        return new Cell(0,0,0,this);
    }
    public boolean checkRules(){
        for(int i = 0; i < 9; i++){
            if(!(boxes[i].checkValid())){
                return false;
            }
            if(!(rows[i].checkValid())){
                return false;
            }
            if(!(cols[i].checkValid())){
                return false;
            }
        }
        return true;
    }


    public void printBoard(){
        for(Cell[] row : cells){
            for(Cell cell : row){
                System.out.print(cell.getVal() + " ");
            }
            System.out.println();
        }
    }

    public Set[] getRows() {
        return rows;
    }

    public Set[] getCols() {
        return cols;
    }
}
