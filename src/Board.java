public class Board {

    private Box[][] boxes;

    public Board(int[][] values){
        boxes = new Box[3][3];
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                boxes[i][j] = new Box(i, j);
            }
        }
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                int boxRow = i/3;
                int boxCol = j/3;
                boxes[boxRow][boxCol].addCell(values[i][j], i - 3*boxRow, j - 3*boxCol);
            }
        }
    }

    
    public Box[][] getBoxes() {
        return boxes;
    }

}
