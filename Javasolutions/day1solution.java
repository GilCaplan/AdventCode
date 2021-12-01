import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day1solution {		
	public static Scanner reader= new Scanner(System.in);
	public static void main(String args[]){
		List<Integer> input = new ArrayList<Integer>();
		int i = reader.nextInt(), cnt=0;
		while(i != -1) {
			input.add(i);
			i = reader.nextInt();
		}
		System.out.println(part1(input));
		System.out.println(part2(input));
	}
	public static int part1(List<Integer> input) {
		int cnt = 0, i=0;
		while(i < input.size() -1) {
			if (input.get(i)< input.get(i+1)){
				cnt ++;
			}
			i++;
		}
		return cnt;
	}
	public static int part2(List<Integer> input) {
		int col = 0, cnt=0;
		while (col < input.size() - 3){
			if (input.get(col) + input.get(col+1)+ input.get(col+2) < input.get(col+1) + input.get(col+2)+ input.get(col+3))
				cnt++;
			col++;
		}
		return cnt;
	}
}


