import java.util.ArrayList;
import java.util.HashMap;

public class Cell {

    private int val, row, col;
    private Board parent;
    private ArrayList<Integer> options;
    private boolean usedToClear;
    private HashMap<String, Set> sets;

    public Cell(int val, int row, int col, Board parent) {
        this.val = val;
        this.row = row;
        this.col = col;

        sets = new HashMap<String, Set>();
        this.parent = parent;
        if (this.val == 0) {
            options = new ArrayList<Integer>();
            for (int i = 1; i < 10; i++) {
                options.add(i);
            }
        }
        usedToClear = false;

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
            for(String s : sets.keySet()){
                sets.get(s).update();
            }
            for(Set row : parent.getRows()){
                row.checkPointingPairs();
            }
            for(Set col : parent.getCols()){
                col.checkPointingPairs();
            }
        }
    }


    public void clearOption(){
        for(String s : sets.keySet()){
            for(Cell cell : sets.get(s).getCells()){
                cell.removeOption(val);
            }
        }
        usedToClear = true;
    }


    public boolean hasOption(int option){
        return (options != null && options.indexOf(option) != -1);

    }

    public HashMap<String, Set> getSets() {
        return sets;
    }

    public void setVal(int val){
        if(getVal() == 0){
            options = new ArrayList<Integer>();
            options.add(val);
            fill();
        }

    }

    public ArrayList<Integer> getOptions(){
        return options;
    }
}
