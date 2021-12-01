import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day1solution {		
	public static Scanner reader= new Scanner(System.in);
	public static void main(String args[]){
		int[] input = new int[2000];
		int i = reader.nextInt(), cnt=0;
		while(i != -1) {
			input[cnt] = i;
			cnt++;
			i = reader.nextInt();
		}
		System.out.println(part1(input));
		System.out.println(part2(input));
	}
	public static int part1(int[] input) {
		int cnt = 0, i=0;
		while(i < input.length -1) {
			if (input[i] < input[i + 1]){
				cnt ++;
			}
			i++;
		}
		return cnt;
	}
	public static int part2(int[] input) {
		int col = 0, cnt=0;
		while (col < input.length - 3){
			if (input[col] + input[col +1]+ input[col + 2] < input[col + 1] + input[col +2]+ input[col + 3] )
				cnt++;
			col++;
		}
		return cnt;
	}
}



	
//try {
//File myObj = new File("C:/Users/avaca/eclipse-workspace/Advent2021");        
//Scanner myReader = new Scanner(myObj);
//while (myReader.hasNextLine()) {
//    String data = myReader.nextLine();
//    System.out.println(data);
//}
//myReader.close();
//} catch (FileNotFoundException e) {
//System.out.println("An error occurred.");
//e.printStackTrace();
//}
