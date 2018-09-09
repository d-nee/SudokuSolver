public class Main {

    public static void main(String[] args) {
        Reader r = new Reader();
        Board b = new Board(r.loadPuzzle("Puzzles/puzzle1.txt"));
        b.printBoard();
        while(!b.isSolved()) {
            b.fillNakedSingles();
        }
        System.out.println();
        b.printBoard();


    }

}
