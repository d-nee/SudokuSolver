public class Main {

    public static void main(String[] args) {
        Reader r = new Reader();
        for (int i = 1; i < 16; i++) {
            Board b = new Board(r.loadPuzzle("Puzzles/puzzle" + i + ".txt"));
            b.printBoard();
            b.fillNakedSingles();
            System.out.println();
            b.printBoard();
            System.out.println("Puzzle " + i + "\n\n");

        }



    }

}
