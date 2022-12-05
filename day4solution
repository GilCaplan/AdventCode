public static void main(String[] args) throws FileNotFoundException {
		List<String[]> input = getinput("day4input");
		part1(input);//A,X=rock, B,Y =paper, C,Z=Scissors
		part2(input);
	}
	public static void part2(List<String[]> input) {
		int cnt = 0;int firstL,firstR,secondL,secondR;
		for(String[] pair : input) {
			firstL = Integer.valueOf(pair[0].split("-")[0]);
			firstR = Integer.valueOf(pair[0].split("-")[1]);
			secondL = Integer.valueOf(pair[1].split("-")[0]);
			secondR = Integer.valueOf(pair[1].split("-")[1]);
			cnt+= checkRangep2(firstL,firstR,secondL,secondR);
		}
		System.out.println(cnt);
	}
	public static void part1(List<String[]> input) {
		int cnt = 0;int firstL,firstR,secondL,secondR;
		for(String[] pair : input) {
			firstL = Integer.valueOf(pair[0].split("-")[0]);
			firstR = Integer.valueOf(pair[0].split("-")[1]);
			secondL = Integer.valueOf(pair[1].split("-")[0]);
			secondR = Integer.valueOf(pair[1].split("-")[1]);
			cnt+= checkRangep1(firstL,firstR,secondL,secondR);
		}
		System.out.println(cnt);
	}
	public static int checkRangep1(int firstL, int firstR, int secondL, int secondR) {
		if(firstL <= secondL && firstR >= secondR)
			return 1;//second range is in the first range
		if(firstL >= secondL && firstR <= secondR)
			return 1;//first range is in the second range
		return 0;
	}
	public static int checkRangep2(int firstL, int firstR, int secondL, int secondR) {
		//if doesn't overlap then return 0;
		if(secondL > firstR)
			return 0;
		if(firstL > secondR)
			return 0;
		return 1;
	}
	public static List<String[]> getinput(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("C:\\Users\\USER\\eclipse-workspace\\advent2022\\src\\inputs\\"+filename+".txt"));
		List<String[]> list = new ArrayList<String[]>();
		while (reader.hasNextLine())
	        list.add(reader.nextLine().split(","));
		return list;
	}
