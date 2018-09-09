public class Main {

    public static void main(String[] args) {
        Reader r = new Reader();
        Board b = new Board(r.loadPuzzle("Puzzles/puzzle1.txt"));
        b.printBoard();
        int iterations = 0;
        while(!b.isSolved()) {
            b.fillNakedSingles();
            iterations++;
        }
        System.out.println();
        b.printBoard();
        System.out.println();
        System.out.println("Iterations: " + iterations);
    }
}
