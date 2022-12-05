public class day5 {
	public static int len = 9;
	public static List<String[]> commands;
	public static void main(String[] args) throws FileNotFoundException {
		commands = commands(getinput("day5input"));
		List<Stack<String>> table = inputStacks(getinput("day5Table"));
		part1(table);//A,X=rock, B,Y =paper, C,Z=Scissors
		table = inputStacks(getinput("day5Table"));
		part2(table);
	}
	public static void part2(List<Stack<String>> table) {
		List<String> crates = new ArrayList<String>();
		for(String[] c: commands) {
			crates = new ArrayList<String>();
			for(int i=0; i < Integer.valueOf(c[0]); i++) {
				crates.add(table.get(Integer.valueOf(c[1])-1).pop());
			}
			for(int i=Integer.valueOf(c[0]); i > 0 ;i--) {
				table.get(Integer.valueOf(c[2])-1).push(crates.get(i-1));
			}
		}
		System.out.println();
		for(Stack<String> stack : table) {
			System.out.print(stack.peek());
		}
	}

	public static void part1(List<Stack<String>> table) {
		for(String[] c: commands) {
			for(int i=0; i < Integer.valueOf(c[0]);i++) {
				table.get(Integer.valueOf(c[2])-1).push(table.get(Integer.valueOf(c[1])-1).pop());
			}
		}
		for(Stack<String> stack : table) {
			System.out.print(stack.peek());
		}
	}
	public static List<String[]> commands(List<String> input){
		List<String[]> commands = new ArrayList<String[]>();
		for(String line : input) {
			line = line.replaceAll("move ", "");
			line = line.replaceAll("from ", "");
			line = line.replaceAll("to ","");
			commands.add(line.split("[\s]+"));
		}
		return commands;
	}
	public static List<Stack<String>> inputStacks(List<String> tableInput){
		List<Stack<String>> table = new ArrayList<Stack<String>>();
		String[] row = new String[tableInput.size()-1];
		String r = "";
		for(int i = 0; i < len; i++) {
			table.add(new Stack<String>());
		}
		for(int i = tableInput.size()-2; i>-1;i--) {//loop through each row
			r = tableInput.get(i).replaceAll("    ", "[e] ").replaceAll("]", "").replaceAll("\\[", "").replace(" ", "");
			for(int j = 0; j<r.length();j++) {
				if(r.charAt(j) != 'e') {
					table.get(j).push(String.valueOf(r.charAt(j)));//add letter to the stack
				}
			}
			
		}
		return table;
	}
	public static List<String> getinput(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("C:\\Users\\USER\\eclipse-workspace\\advent2022\\src\\inputs\\"+filename+".txt"));
		List<String> list = new ArrayList<String>();
		while (reader.hasNextLine())
	        list.add(reader.nextLine());
		return list;
	}
}
