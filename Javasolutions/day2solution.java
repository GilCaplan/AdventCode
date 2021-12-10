import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day2solution {
	public static Scanner reader= new Scanner(System.in);
	public static void main(String args[]){
		List<String> input = new ArrayList<String>();
		String s="";
		File inputFile = new File("/day2_input.txt");
		Scanner scan;
		try {
			scan = new Scanner(inputFile);
			while(scan.hasNext()) {
				s = scan.nextLine();
				input.add(s);
			}
			System.out.println("input received");
			System.out.println(part1(input));
			System.out.println(part2(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public static int part1(List<String> input) {
		int foward=0;
		int depth =0;
		for(int i=0;i< input.size();i++) {
			String[] arr = input.get(i).split("\\s");
			if(arr[0].equals("forward"))
				foward+= Integer.valueOf(arr[1]);
			else 
				if(arr[0].equals("down"))
					depth+= Integer.valueOf(arr[1]);
			else	
				if(arr[0].equals("up"))
					depth-= Integer.valueOf(arr[1]);
		}
		return depth*foward;
	}
	public static int part2(List<String> input) {
		int foward=0, depth =0, aim = 0;
		for(int i=0;i< input.size();i++) {
			String[] arr = input.get(i).split("\\s");
			if(arr[0].equals("forward")) {
				foward+= Integer.valueOf(arr[1]);
				depth+= aim*Integer.valueOf(arr[1]);
			}
			else 
				if(arr[0].equals("down"))
					aim+= Integer.valueOf(arr[1]);
			else	
				if(arr[0].equals("up"))
					aim-= Integer.valueOf(arr[1]);
		}
		return depth*foward;
	}
}