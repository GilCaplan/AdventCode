import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class day14solution {
	public static void main(String args[]) throws FileNotFoundException{
		File inputFile = new File("/day14_input.txt");
		HashMap<String, String> input = new HashMap<String, String>();
		HashMap<String, Long> pairs = new HashMap<String, Long>();
		Scanner scan = new Scanner(inputFile); 
		String str = scan.nextLine();String[] rules;
		scan.nextLine();
		while(scan.hasNext()) {
			rules = scan.nextLine().split(" -> ");
			input.put(rules[0], rules[1]);
		}
		scan.close();
		
		for(int i=0; i< str.length() - 1 ; i++) {
			String key = str.substring(i,i+2);
			if(pairs.containsKey(key))
				pairs.put(key, pairs.get(key) + 1);
			else
				pairs.put(key, (long) 1);
		}
		String last = String.valueOf(str.charAt(str.length()-1));//save last one since it won't be counted in the pairs
		System.out.println("part 1 solution is: " + solution(pairs, input,  10, last));
		System.out.println("part 2 solution is: " + solution(pairs, input,  40, last));

	}

	public static long solution(HashMap<String, Long> pairs, HashMap<String, String> rules, int times, String last) {
		HashMap<String, Long> newPairs = new HashMap<String, Long>();
		String s1,s2;long n;
		for(int j =0 ; j< times; j++) {
		 
			for(String key : pairs.keySet()) {
				s1 = key.substring(0,1) + rules.get(key);//left string
				s2 = rules.get(key) + key.substring(1,2);//right string
				n =  pairs.get(key);//amount of times we already have the string
				//add it and if we already have it then add n to previous value
				newPairs.put(s1, newPairs.containsKey(s1)? newPairs.get(s1) + n : n);
				newPairs.put(s2, newPairs.containsKey(s2)? newPairs.get(s2) + n : n);
			}
			pairs = (HashMap<String, Long>) newPairs.clone();
			newPairs = new HashMap<String, Long>();
		}
	
		return cntMaxMin(pairs, last);//times most by little
	}
	public static long cntMaxMin(HashMap<String, Long> pairs, String last) {
		long max=0, min = Long.MAX_VALUE;String check="";
		HashMap<String, Long> count = new HashMap<String, Long>();
		count.put(last, (long) 1);
		for(String str : pairs.keySet()) {
			check = String.valueOf(str.substring(0,1));
			if(!count.containsKey(check))
				count.put(check,  pairs.get(str));
			else
				count.put(check, count.get(check) + pairs.get(str));
		}
		for(String s : count.keySet()) {
			max = Math.max(max, count.get(s));
			min = Math.min(min, count.get(s));
		}
		return max - min;
	}

}
