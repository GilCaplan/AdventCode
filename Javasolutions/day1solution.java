public class day1solution {		
	public static Scanner reader= new Scanner(System.in);
	public static void main(String args[]){
		List<Integer> input = new ArrayList<Integer>();
		String s="";
		File inputFile = new File("/C:/Users/avaca/eclipse-workspace/Advent2021/day1_input.txt");
		Scanner scan;
		try {
			scan = new Scanner(inputFile);
			while(scan.hasNext()) {
				s = scan.nextLine();
				input.add(Integer.valueOf(s));
			}
			System.out.println("input received");
			System.out.println(part1(input));
			System.out.println(part2(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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



