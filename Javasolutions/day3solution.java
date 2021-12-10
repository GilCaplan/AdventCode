import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class day3solution {
	public static Scanner reader= new Scanner(System.in);
	public static void main(String args[]){
		List<String> input = new ArrayList<String>();
		String s="";
		File inputFile = new File("/day3_input.txt");
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
		int len = input.get(0).length();
		String gamma="", epsilon="";//p=g*e
		String s = "";
		int[] cnt1 = new int[len];
		int[] cnt0 = new int[len];
		for(int i=0;i < input.size(); i++) {
			for(int j = 0 ;j < len; j++) {
				s = String.valueOf(input.get(i).charAt(j));
				if (s.equals("1"))
					cnt1[j]++;
				else 
					cnt0[j]++;
			}
		}
		for(int i=0;i<len;i++) {
			if (cnt1[i]>cnt0[i]) {
				gamma += "1";
				epsilon += "0";
			}
			else{
				gamma += "0";
				epsilon+= "1";
			}
		}
		return (int) (Long.parseLong(gamma,2)*Long.parseLong(epsilon,2));
	}
	public static int part2(List<String> input) {
		String oxg="",co2="";	
		int len = input.get(0).length();String s="";
		int[] cnt1 = new int[len];
		int[] cnt0 = new int[len];
		List<String> list1 = new LinkedList<String>();
		List<String> list0 = new LinkedList<String>();
		List<String> newinput= input.stream()
				  .collect(Collectors.toList());
		for(int j = 0 ;j < len; j++) {
			for(int i=0;i < newinput.size(); i++) {
				s = String.valueOf(newinput.get(i).charAt(j));
				if (s.equals("1")) {
					cnt1[j]++;
					list1.add(newinput.get(i));
				}
				else {
					cnt0[j]++;
					list0.add(newinput.get(i));
				}
			}
			if (cnt1[j] >= cnt0[j]) {
				newinput = list1;			
				if(list1.size() == 1)
					oxg = list1.get(0);
			}
			else
			{
				newinput = list0;
				if(list0.size() == 1)
					oxg = list0.get(0);
			}
			list1 = new LinkedList<String>();
			list0 = new LinkedList<String>();
			cnt1 = new int[len];
			cnt0 = new int[len];
		}
		cnt1 = new int[len];
		cnt0 = new int[len];
		list1 = new LinkedList<String>();
		list0 = new LinkedList<String>();
		newinput= input.stream()
				  .collect(Collectors.toList());
		for(int j = 0 ;j < len; j++) {
			for(int i=0;i < newinput.size(); i++) {
				s = String.valueOf(newinput.get(i).charAt(j));
				if (s.equals("1")) {
					cnt1[j]++;
					list1.add(newinput.get(i));
				}
				else {
					cnt0[j]++;
					list0.add(newinput.get(i));
				}
			}
			if (cnt1[j] >= cnt0[j]) {
				newinput = list0;	
				if(list0.size() == 1)
					co2 = list0.get(0);
			}
			else
			{
				newinput = list1;
				if(list1.size() == 1)
					co2 = list1.get(0);
			}
			list1 = new LinkedList<String>();
			list0 = new LinkedList<String>();
			cnt1 = new int[len];
			cnt0 = new int[len];
		}
		return (int) (Long.parseLong(oxg,2)*Long.parseLong(co2,2));
	}

}