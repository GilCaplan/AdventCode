public static void main(String[] args) throws FileNotFoundException {
		List<String> input = getinput("day1input");
		part1(input);
		part2(input);
	}
	public static void part2(List<String> input) {
		int sum = 0, checkCalories=0;
		List<Integer> calories = new ArrayList<Integer>();
		for(String calorie : input) {
			if(calorie.length() > 0) {
				checkCalories+= Integer.valueOf(calorie);
			}
			else {
				calories.add(checkCalories);
				checkCalories=0;
			}
		}
		int max1=0,max2=0,max3=0;
		for(int calorie: calories) {
			if(calorie > max1) {
				max3 = max2;
				max2 = max1;
				max1 = calorie;
			}
			else if(calorie > max2) {
				max3 = max2;
				max2 = calorie;
			}
			else if(calorie > max3) {
				max3 = calorie;
			}
		}
		sum= max1+max2+max3;
		System.out.println("answer to part 2 is: "+ sum);
	}
	public static void part1(List<String> input) {
		int sum = 0, checkCalories=0;
		for(String calorie : input) {
			if(calorie.length() > 0) {
				checkCalories+= Integer.valueOf(calorie);
			}
			else {
				sum = Math.max(sum, checkCalories);
				checkCalories=0;
			}
		}
		System.out.println("answer to part 1 is: " + sum);
	}
	public static List<String> getinput(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("C:\\Users\\USER\\eclipse-workspace\\advent2022\\src\\inputs\\"+filename+".txt"));
		List<String> list = new ArrayList<String>();
		while (reader.hasNextLine()) {
	        list.add(reader.nextLine());
		}
		return list;
	}
