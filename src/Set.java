import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
public class Set {

    private Cell[] cells;
    private HashMap<Integer, ArrayList<Cell>> optionsList;
    private HashMap<Integer, ArrayList<ArrayList<Cell>>> commonOptions;
    private String name;
    public Set(Cell[] cells, String name){
        this.cells = cells;
        this.name = name;
        optionsList = new HashMap<Integer, ArrayList<Cell>>();
        for (int i = 1; i < 10; i++) {
            optionsList.put(i, new ArrayList<Cell>());
            for(Cell cell : cells){
                optionsList.get(i).add(cell);
                cell.getSets().put(name, this);
            }
        }

//        commonOptions = new HashMap<Integer, ArrayList<ArrayList<Cell>>>();
        update();
    }

    public void update(){
//        commonOptions.clear();
        for (int i = 1; i < 10; i++) {

            if(optionsList.get(i) != null) {
                for (Cell cell : cells) {
                    //if there is a cell in set with this val, remove from optionsList
                    if (cell.getVal() == i) {
                        optionsList.remove(i);
                        break;
                    }

                    //if cell does not have the option, remove it from optionslist
                    if (!cell.hasOption(i)) {
                        optionsList.get(i).remove(cell);
                    }
                    else{

                    }
                }
            }
        }

        for (int i = 1; i < 10; i++) {
            //Hidden Singles - If a cell is the last cell in its set a given option,
            //set that cell to that option regardless of how many other options that cell still has.
            if (optionsList.get(i) != null) {
                ArrayList<Cell> list = optionsList.get(i);
                if (list.size() == 1) {
                    list.get(0).setVal(i);
                }
            }
        }
    }

    public Cell[] getCells() {
        return cells;
    }


    public boolean checkLists(ArrayList<Integer> a, ArrayList<Integer> b){
        int sum = 0;
        for(Integer c : a){
            for(Integer c1 : b){
                if(c.equals(c1)){
                    sum++;
                    break;
                }
            }
        }
        return sum == a.size() && a.size() == b.size();
    }

    /* I doubt the implementation of these next two methods is well optimized,
    but its not like this program is a heavy lead on the computer (yet) */
    public void checkIntersections(){
        //Pointing Pairs/Triples, Box-Line Reduction.
        //After an option is removed, a cell asks its Col and Row to check for intersections.
        for (int i = 1; i < 10; i++) {
            ArrayList<Cell> hasOption = optionsList.get(i);
            //For a given option in the row/col
            if(hasOption != null) {
                for (Cell cell : hasOption) {
                    Set box = cell.getSets().get("box");
                    ArrayList<Cell> boxOption = box.getOptionsList().get(i);
                    /* If the set of cells with that option in the row is a subset of the cells within a box,
                    OR if the cells in the box with that option is a subset of the cells in a row/col,
                    remove the option from cells in the larger set that are not part of the subset. */
                    if (boxOption != null && boxOption.size() != hasOption.size()) {
                        if (boxOption.containsAll(hasOption)) {
                            box.clearAllFromOption(i, hasOption);
                            break;
                        } else if (hasOption.containsAll(boxOption)) {
                            clearAllFromOption(i, boxOption);
                            break;
                        }
                    }
                }
            }
        }
    }
    public void nakedMultis(){
        //Naked Doubles, Triples, Quads
        for(Cell cell1: cells) {
            if (cell1.getVal() == 0 && cell1.getOptions().size() < 5) {
                ArrayList<Cell> containsOptions = new ArrayList<Cell>();
                for (Cell cell2 : cells) {
                    /* Check if the cell contains the optionsList of other cells in the set, given the length of the longest optionList
                    less than 5. If so, add them to a set. If that set contains the same number of cells as options in the original cell,
                    remove those options from cells that are do not have an optionList that is a sublist of the original cell's optionList. */
                    if (cell2.getVal() == 0 && cell1.getOptions().containsAll(cell2.getOptions())) {
                        containsOptions.add(cell2);
                    }
                }
                Integer[] optionsToClear = new Integer[cell1.getOptions().size()];
                cell1.getOptions().toArray(optionsToClear);
                if (cell1.getOptions().size() == containsOptions.size()) {
                    for (Integer a: optionsToClear) {
                        clearAllFromOption(a, containsOptions);
                    }
                }
            }
        }
    }

    public boolean hasCell(Cell c){
        return c.getSets().get(this.name).equals(this);
    }

    public boolean checkValid(){
        Integer[] nums = new Integer[getVals().size()];
        getVals().toArray(nums);
        for(int a = 0; a < nums.length;a++){
            for(int b = 0; b < nums.length; b++){
                if(nums[a] == nums[b] && a != b){
                    return false;
                }
            }
        }
        return true;
    }

    //Clears an a given option, except for given cells (the subset found in checkIntersections)
    public void clearAllFromOption(int option, ArrayList<Cell> exclude){
        if(optionsList.get(option) != null) {
            ArrayList<Cell> optionList = optionsList.get(option);
            for (int i = optionList.size() - 1; i >= 0; i--) {
                if (optionList.size() > i) {
                    if (exclude != null) {
                        boolean canRemove = true;
                        for (Cell c : exclude) {
                            if (c.equals(optionList.get(i))) {
                                canRemove = false;
                                break;
                            }
                        }
                        if (canRemove) {
                            optionList.get(i).removeOption(option);
                        }
                    } else {
                        optionList.get(i).removeOption(option);
                    }
                }
            }
        }
    }
    public ArrayList<Integer> getVals(){
        ArrayList<Integer> vals = new ArrayList<Integer>();
        for(Cell cell: cells){
            if(cell.getVal() != 0){
                vals.add(cell.getVal());
            }
        }
        return vals;
    }

    public HashMap<Integer, ArrayList<Cell>> getOptionsList() {
        return optionsList;
    }
}
