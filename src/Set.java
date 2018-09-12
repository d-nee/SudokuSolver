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

    public void checkNakedMultis(){

    }

    public boolean checkLists(ArrayList<Object> a, ArrayList<Object> b){
        int sum = 0;
        for(Object c : a){
            for(Object c1 : b){
                if(c.equals(c1)){
                    sum++;
                    break;
                }
            }
        }
        return sum == a.size() && a.size() == b.size();
    }

    public void checkNakedDoubles(){
//        int start = 1;
//        ArrayList<ArrayList<Cell>> cellPair = new ArrayList<ArrayList<Cell>>();
//
//        while(getOptionsOfSize(2, start) != -1){
//            cellPair.add(optionsList.get())
//        }
    }

    public int getCellOfSize(int size, int start){
        for (int i = start; i < 10; i++) {
            if(cells[i].getOptions().size() == size){
                return i;
            }
        }
        return -1;
    }

    public void checkPointingPairs(){
        for (int i = 1; i < 10; i++) {
            ArrayList<Cell> hasOption = optionsList.get(i);
            if(hasOption != null) {
                for (Cell cell : hasOption) {
                    Set box = cell.getSets().get("box");
                    ArrayList<Cell> boxOption = box.getOptionsList().get(i);
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

    public boolean hasCell(Cell c){
        return c.getSets().get(this.name).equals(this);
    }

    public void clearAllFromOption(int option, ArrayList<Cell> exclude){

        ArrayList<Cell> optionList = optionsList.get(option);

        for (int i = optionList.size() - 1; i >= 0; i--) {
            if(optionList.size() > i) {
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

    public HashMap<Integer, ArrayList<Cell>> getOptionsList() {
        return optionsList;
    }
}
