import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day10 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String[]> input = getinput("input10");
        System.out.println(part1(input));
        part2(input);

    }

    public static void part2(List<String[]> ls){
        int[][] crt = new int[6][40];
        int reg = 1, row = 0, col = 0;
        for(String[] str : ls){
            if(col >= 40){
                col=0;
                row++;
            }
            if(col <= reg + 1 && col >= reg - 1)// reg - 1 <= col <= reg + 1, check if sprite loc in range
                crt[row][col] = 1;//draw
            col++;
            if(!str[0].equals("noop")) {
                if(col >= 40){
                    col=0;
                    row++;
                }
                if(col <= reg +1 && col >= reg -1)// reg - 1 <= col <= reg + 1, check if sprite loc in range
                    crt[row][col] = 1;//draw
                col++;
            }

            if(str.length == 2)
                reg+= Integer.parseInt(str[1]);
        }

        for (int i=0; i < crt.length; i++){//print screen
            for (int j=0; j < crt[0].length; j++){
                System.out.print(crt[i][j] == 1 ? "#":".");
            }
            System.out.println();
        }
    }
    public static int part1(List<String[]> ls){
        int sum = 0;
        int reg = 1;
        int cycle = 0;
        for(String[] str : ls){
            cycle++;
            if(cycle <= 220 && ((cycle-20) % 40)  == 0 )
                sum += cycle * reg;
            if(!str[0].equals("noop")) {
                cycle++;
                if (cycle <= 220 && ((cycle - 20) % 40) == 0)
                    sum += cycle * reg;

            }

            if(str.length == 2)
                reg+= Integer.parseInt(str[1]);
        }
        return sum;
    }

    public static @NotNull List<String[]> getinput(String filename) throws FileNotFoundException {
        var reader = new Scanner(new File("C:\\Users\\USER\\IdeaProjects\\AdventOfCode_Gil_2022\\src\\input10.txt"));
        var list = new ArrayList<String[]>();
        while (reader.hasNextLine())
            list.add(reader.nextLine().split(" "));
        return list;
    }
}
