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

    public void use(){
        usedToClear = true;
    }

    public boolean getUsedToClear(){
        return usedToClear;
    }

    public int getOptionsSize(){
        return options.size();
    }

    public void fill(){
        val = options.get(0);
    }


    public int getVal() {
        return val;
    }

    public void removeOption(int option) {
        //Please make sure you check if the val is 0 before using this method!
        if(options.indexOf(option) != -1) {
            options.remove(options.indexOf(option));
        }
    }


}
