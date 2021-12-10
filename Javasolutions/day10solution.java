import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;

public class day10solution {
	public static HashMap<String,String> Pair = new HashMap<String,String>();
	public static HashMap<String,Integer> value = new HashMap<String,Integer>();
	public static HashMap<String,Integer> points = new HashMap<String,Integer>();
	public static List<String> Isopen = Arrays.asList(new String[] {"[","{","(","<"}); 
	public static void main(String args[]){
		List<String> input = new ArrayList<String>();
		File inputFile = new File("/day10_input.txt");
		Scanner scan;
		Pair.put("[","]");
		Pair.put("{","}");
		Pair.put("<",">");
		Pair.put("(",")");
		value.put("]",57);
		value.put(")",3);
		value.put("}",1197);//part 1
		value.put(">",25137);
		points.put("(",1);//part 2
		points.put("[",2);
		points.put("{",3);
		points.put("<",4);
		try {
			scan = new Scanner(inputFile);
			while(scan.hasNext()) {
				input.add(scan.nextLine());
			}
			System.out.println("input received");
			System.out.println(part1(input));
			System.out.println(part2(input));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static int part1(List<String> input) {
		int sum =0,num=0;List<String> newLine = new ArrayList<String>();
		for(String line : input) {
			for(String s : line.split("")) {
				newLine.add(s);
			}
			num =getErrorNum(newLine);
			sum+= num == -1?0:num;
			newLine = new ArrayList<String>();
		}
		return sum;
	}
	public static int getErrorNum(List<String> line){
		Stack<String> check = new Stack<String>();
		for(int i=0;i<line.size();i++) {
			if(Isopen.contains(line.get(i)))
				check.push(line.get(i));
			else if(Pair.get(check.peek()).equals(line.get(i))) {
				check.pop();
			}
			else {
				return value.get(line.get(i));
			}
		}
		if(!check.isEmpty())
			return -1;
		return 0;
	}
	public static String getClose(List<String> line) {
		for(int i = 1 ; i< line.size(); i++) {
			if(line.get(i).equals("]") || line.get(i).equals("}") || line.get(i).equals("}") ||line.get(i).equals(")") ) {
				if(!Pair.get(line.get(i-1)).equals(line.get(i)))
					return line.get(i);
			}
		}
		return "";
	}
	public static long part2(List<String> input) {
		List<Long> sum =new LinkedList<Long>();
		List<String> newLine = new ArrayList<String>();
		List<String> incomplete = new ArrayList<String>();
		
		for(String line : input) {
			for(String s : line.split("")) {
				newLine.add(s);
			}
			if(getErrorNum(newLine) == -1) {
				incomplete.add(line);
			}
			newLine = new ArrayList<String>();
		}
		for(String letsComplete: incomplete) {
			sum.add(CompleteScore(letsComplete));
		}
		
		Collections.sort(sum);
		int middle = sum.size()/2 ;
		System.out.println(sum.size()/2 + " "+ sum.size());
		return sum.get(middle);
	}
	public static long CompleteScore(String line) {
		Stack<String> check = new Stack<String>();
		for(int i=0;i<line.length();i++) {
			if(Isopen.contains(String.valueOf(line.charAt(i))))
				check.push(String.valueOf(line.charAt(i)));
			else if(Pair.get(check.peek()).equals(String.valueOf(line.charAt(i))))
				check.pop();
		}
		long score=0;
		while(!check.isEmpty()) {
			score *=5;
			score += points.get(check.pop());
		}
		return score;
	}
}
