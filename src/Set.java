import java.util.ArrayList;
import java.util.HashMap;

public class Set {

    private Cell[] cells;
    private HashMap<Integer, ArrayList<Cell>> optionsList;
    public Set(Cell[] cells, String name){
        this.cells = cells;
        optionsList = new HashMap<Integer, ArrayList<Cell>>();
        for (int i = 1; i < 10; i++) {
            optionsList.put(i, new ArrayList<Cell>());
            for(Cell cell : cells){
                optionsList.get(i).add(cell);
                cell.getSets().put(name, this);
            }
        }
        update();
    }

    public void update(){
        for (int i = 1; i < 10; i++) {
            for(Cell cell : cells){
                if(!cell.hasOption(i)){
                    optionsList.get(i).remove(cell);
                }
            }
        }
        for (int i = 1; i < 10; i++) {
            ArrayList<Cell> list = optionsList.get(i);
            if(list.size() == 1){
                list.get(0).setVal(i);
            }
        }
    }

    public Cell[] getCells() {
        return cells;
    }
}
