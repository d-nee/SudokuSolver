public class Main {

    public static void main(String[] args) {
        Reader r = new Reader();
        Board b = new Board(r.loadPuzzle("Puzzles/puzzle1.txt"));
        for(Box[] boxes : b.getBoxes()){
            for(Box box : boxes){
                for(Cell[] cells : box.getCells()){
                    for(Cell cell : cells){
                        System.out.println(cell.getVal() + "");
                    }
                    System.out.println("\n");
                }
                System.out.println("\n");
            }
            System.out.println("\n\n");
        }
    }

}
