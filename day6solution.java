import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class day6 {
	public static void main(String[] args) throws FileNotFoundException {
		String input = getinput("day6input");
		int part1=4,part2=14;
		solve(input, part1);
		solve(input, part2);
	}
	
	public static void solve(String input, int len) {
		char[] marker = new char[len];
		for(int j = 0;j< marker.length;j++) {
			marker[j] = ' ';
		}
		int cnt=0;
		for(int i=0; i<input.length()-len-1;i++) {
			for(int j= 0; j< len;j++) {
				marker[j] = input.charAt(i+j);
			}
			if(containsDuplicate(marker)){//true
				for(int j = 0;j< marker.length;j++) {
					marker[j] = ' ';
				}
				cnt++;
			}
			if(marker[3] != ' ') {
				System.out.println(cnt+len);
				return;
			}
		}
	}
	public static boolean containsDuplicate(char[] marker) {
		for(int i=0; i< marker.length;i++) {
			for(int j=0; j< marker.length;j++) {
				if(i!=j && marker[i] == marker[j])
					return true;
			}
		}
		return false;
	}
	public static boolean containsLetter(String[] marker, String letter) {
		for(String l : marker) {
			if(l != null && l.equals(letter))
				return true;
		}
		return false;
	}
	public static String getinput(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("C:\\Users\\USER\\eclipse-workspace\\advent2022\\src\\inputs\\"+filename+".txt"));
		return reader.nextLine();
	}
}
