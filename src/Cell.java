import java.util.ArrayList;

public class Cell {
    private int row, column, value;
    private ArrayList<Integer> candidates = new ArrayList<Integer>();

    public Cell(int r, int c, int v){
        row = r;
        column = c;
        value = v;
        if(value == 0) {
            candidates = new ArrayList<Integer>();
            for(int i  = 1; i < 10; i++){
                candidates.add(i);
            }

        }
    }

    public void removeCandidate(int candidate){
        candidates.remove(candidate);
    }

    public int getValue(){
        return value;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
