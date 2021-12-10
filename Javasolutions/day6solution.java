import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class day6 {
	public static void main(String args[]) throws FileNotFoundException{
		String[] input;long[] cnt= new long[9];
		File inputFile = new File("/day6_input.txt");
		Scanner scan = new Scanner(inputFile); 
			input =  scan.nextLine().split(",");
			scan.close();
			for(String f : input) 
				cnt[Integer.valueOf(f)]++;
			System.out.println(solution(cnt, 256));//change 256 to 80 for part 1 solution
	}
	public static long solution(long[] fishes, int amount) {	
		long newfish=0;
		for(int i = 0; i < amount ; i++) {
			if(fishes[0] > 0 ) 
				newfish = fishes[0];
			for(int fish = 1; fish < fishes.length ; fish++) {
				fishes[fish - 1] = fishes[fish];
				fishes[fish] = 0;
			}
			fishes[8]+= newfish;
			fishes[6]+= newfish;
			newfish = 0;
		}
		return Arrays.stream(fishes).sum();
	}
}
