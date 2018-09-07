import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Reader {

    public Reader(){

    }

    public int[][] loadPuzzle(String file){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
        String puzString = "";
        try{
            String line = input.readLine();
            while(line.length() > 0){
                puzString += line + "\n";
                line = input.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        int[][] puzzle = new int[9][9];
        String[] lines = puzString.split("\n");
        int r = 0, c;
        for(String l : lines){
            String[] nums = l.split(" ");
            c = 0;
            for(String num:nums){
                puzzle[r][c] = Integer.parseInt(num);
                c++;
            }
            r++;
        }
        return puzzle;
    }

}
