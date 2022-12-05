static HashMap<String,Integer> alphabet = new HashMap<String,Integer>();
	public static void main(String[] args) throws FileNotFoundException {
		List<String> input = getinput("day3input");
		loadMap();
		part1(input);//A,X=rock, B,Y =paper, C,Z=Scissors
		part2(input);
	}
	public static void part1(List<String> input) {
		int sum=0;String cLeft,cRight;
		for(String rucksack : input) {
			cLeft = rucksack.substring(0,rucksack.length()/2);
			cRight = rucksack.substring(rucksack.length()/2,rucksack.length());
			sum += findError(cLeft,cRight);
		}
		System.out.println(sum);
	}
	public static void part2(List<String> input) {
		int sum=0;String elf1,elf2,elf3;
		for(int i=0; i< input.size();i+=3) {
			elf1=input.get(i);elf2=input.get(i+1); elf3=input.get(i+2);
			sum+= findbadge(elf1,elf2,elf3);
		}
		System.out.println(sum);	
	}
	public static int findbadge(String elf1, String elf2, String elf3) {
		for(int i=0; i<elf1.length();i++) {
			for(int j=0;j<elf2.length();j++) {
				if(elf1.charAt(i) == elf2.charAt(j)) {
					if(compareBadge(elf1.charAt(i),elf3))
						return alphabet.get(String.valueOf(elf1.charAt(i)));
				}
			}
		}
		return 0;
	}
	public static boolean compareBadge(char elf, String elf3) {
		for(int i=0; i<elf3.length();i++) {
			if(elf == elf3.charAt(i))
				return true;
		}
		return false;
	}
	public static int findError(String cLeft, String cRight) {
		for(int i=0; i<cLeft.length();i++) {
			for(int j=0;j<cRight.length();j++) {
				if(cLeft.charAt(i) == cRight.charAt(j)) {
					return alphabet.get(String.valueOf(cLeft.charAt(i)));
				}
			}
		}
		return 0;
	}
	public static void loadMap() {
		int value=1;
		for (char ch = 'a'; ch <= 'z'; ++ch) 
			alphabet.put(String.valueOf(ch), value++); 
		for (char ch = 'A'; ch <= 'Z'; ++ch) 
			alphabet.put(String.valueOf(ch), value++); 
		return;
	}
	public static List<String> getinput(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("C:\\Users\\USER\\eclipse-workspace\\advent2022\\src\\inputs\\"+filename+".txt"));
		List<String> list = new ArrayList<String>();
		while (reader.hasNextLine()) 
	        list.add(reader.nextLine());
		return list;
	}
