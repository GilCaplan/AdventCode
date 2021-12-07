import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day7solution {
	public static void main(String args[]) throws FileNotFoundException{
		String[] input;List<Integer> cnt = new ArrayList<Integer>();
		File inputFile = new File("/day7_input.txt");
		Scanner scan = new Scanner(inputFile); 
		input =  scan.nextLine().split(",");
		scan.close();int num,max=0;
		for(String f : input) {
			num = Integer.valueOf(f);
			cnt.add(num);
			max= Math.max(max, num);
		}
		System.out.println(part2(cnt, max));

	}
	public static int part1(List<Integer> input, int max) {
		int sumF=0, min = Integer.MAX_VALUE;
		for(int i = 0; i < max+1; i++) {
			for(int loc : input)
				sumF+= Math.abs(loc - i);
			min = Math.min(min, sumF);
			sumF=0;
		}
		return min;
	}
	public static int part2(List<Integer> input, int max) {
		int sumF=0, min = Integer.MAX_VALUE;
		for(int i = 0; i < max+1; i++) {
			for(int loc : input)
				sumF+= getCost(loc,Math.abs(loc - i));
			min = Math.min(min, sumF);
			sumF=0;
		}
		return min;
	}
	public static int getCost(int num, int moves) {
		int burn=1;int sum=0;
		for(int i =0;i<moves;i++) {
			sum+= burn;
			burn++;num--;
		}
		return sum;
	}
}
