public class Main {

    public static void main(String[] args) {
        Reader r = new Reader();
        System.out.println("-----------------");
        for (int i = 1; i < 33; i++) {
            Board b = new Board(r.loadPuzzle("Puzzles/puzzle" + i + ".txt"));
            System.out.println("Puzzle " + i + ":\n");
            b.printBoard();
            b.fill();
            System.out.println();
            b.printBoard();
            if(b.isSolved()){
                System.out.println("Solved");
            }else{
                System.out.println("Stumped");
            }
            System.out.println("-----------------");
        }

    }

}
