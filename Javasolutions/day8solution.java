import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class day8solution {
	public static void main(String[] args) throws FileNotFoundException{
		List<String[]> input1 = new ArrayList<String[]>(), input2= new ArrayList<String[]>();
		File inputFile = new File("/day8_input.txt");
		Scanner scan = new Scanner(inputFile); String[] ss;
		while(scan.hasNext()) {
			ss = scan.nextLine().split(" \\| ");
			input1.add(ss[0].split("\\s"));
			input2.add(ss[1].split("\\s"));
		}
		scan.close();
		System.out.println(part2(input1, input2));

	}
	public static int part1(List<String[]> input) {
		int cnt = 0;
		for(String[] wires : input) {
			for(String wire : wires) {
				if(wire.length() == 2 || wire.length() == 4 || wire.length() == 3 || wire.length() == 7)
					cnt++;
			}
		}
		return cnt;
	}

    public static int part2(List<String[]> input1, List<String[]> input2) {
        int sum = 0, num;
        for(int i=0; i< input1.size();i++) {
            num =decode(input1.get(i), input2.get(i));
            sum+= num ;
            System.out.println(num);
        }
        return sum;
    }
    public static int decode(String[] pattern, String[] output) {
        var SegmentBuild = getSegmentBuild(pattern);
        StringBuilder num= new StringBuilder();
        for(String o : output) {
            num.append(getSegment(o.strip(), SegmentBuild));// cdgba
        }
        return Integer.valueOf(num.toString());
    }
    private static HashMap<Character, String> getSegmentBuild(String[] pattern) {
        var solution = new HashMap<Character, String>();
        int[] occur=getOccurences(pattern);
        pattern = sortPattern(pattern);
        solution.put('a', pattern[1].replace(String.valueOf(pattern[0].charAt(0)),"").replace(String.valueOf(pattern[0].charAt(1)),""));//a
        solution.put('b', Used(occur, 6,  solution)); //b which is 6times total
        solution.put('e',  Used(occur, 4,  solution));//e 4times
        solution.put('f', Used(occur, 9, solution));//f 9times
        System.out.println(solution.get('f'));
        solution.put('c', pattern[0].replace(solution.get('f'), ""));//c cause in 2segment(piece1) patternand we know f
        // d, g 7times but d is in 04 (pattern[02]) digit pattern
        solution.put('d', Usedg(solution, pattern[2])); //d
        solution.put('g', getLast(solution)); //g
        for (var k : solution.keySet()){
            System.out.print(k+":"+solution.get(k) +", ");
        }
        return solution;
    }

    public static String getLast(HashMap<Character, String> s) {
        String compare="";
        for(String str : s.values()) {
            compare += str;
        }
        String ss = "abcdefg";
        for(int i=0; i< ss.length();i++) {
            var check = String.valueOf(ss.charAt(i));
            if(!compare.contains(check))
                return check;
        }
        return "";
    }
    public static int[] getOccurences(String[] pattern) {
        var hm = new HashMap<String,Integer>(){{
            put("a", 0); put("b", 1); put("c", 2); put("d", 3);
            put("e", 4); put("f", 5); put("g", 6);
        }};
        int[] occur = new int[7];//abcdefg
        for(String pat : pattern) {
            for(int i=0; i< pat.length();i++) {//b is used 6 times, e 4times, f 9 times
                String s = String.valueOf(pat.charAt(i));
                occur[hm.get(s)]++;
            }
        }
        return occur;
    }
    public static String Used(int[] getOccurence, int num, HashMap<Character, String> nope) {
        String str = "abcdefg";
        for(int i = 0; i< getOccurence.length;i++) {
            if(getOccurence[i] == num && !nope.containsValue(String.valueOf(str.charAt(i)))){
                return String.valueOf(str.charAt(i));
            }
        }
        return "g";
    }
    public static String Usedg(HashMap<Character, String> solution, String four) {
        for (String s : solution.values()) {
            four = four.replace(s, "");
        }
        return four;
    }
    public static String[] sortPattern(String[] pattern) {
        String[] newPattern = new String[pattern.length];
        int cnt1=1, cnt2=1;
        for(String s : pattern) {
            if(s.length() == 2)//1
                newPattern[0] = s;
            else if(s.length() == 3)//1
                newPattern[1] = s;
            else if(s.length() == 4)//1
                newPattern[2] = s;
            else if(s.length() == 5) {//3
                newPattern[2+cnt1]=s;
                cnt1++;
            }
            else if(s.length() == 6) {//3
                newPattern[5+cnt2] = s;
                cnt2++;
            }
            else if(s.length() == 7)//1
                newPattern[9]= s;
        }
        return newPattern;
    }

    public static int getSegment(String pattern, HashMap<Character, String> segment) {
        switch(pattern.length()) {//getting the piece according to length
            case 2: return 1;
            case 4: return 4;
            case 3: return 7;
            case 7: return 8;
        }
        if(pattern.length() == 5) {
            if(!pattern.contains(segment.get('b')) && !pattern.contains(segment.get('f'))) {
                return 2;
            }
            if(!pattern.contains(segment.get('b')) && !pattern.contains(segment.get('e'))) {
                return 3;
            }
            return 5;
        }
        if(!pattern.contains(segment.get('e'))) {
            return 9;
        }
        if(!pattern.contains(segment.get('c'))) {
            return 6;
        }
        return 0;
    }
}
