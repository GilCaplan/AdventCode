public static void main(String[] args) throws FileNotFoundException {
		List<String[]> input = getinput("day2input");
		part1(input);//A,X=rock, B,Y =paper, C,Z=Scissors
		part2(input);
	}
	public static void part2(List<String[]> input) {
		//x=lose, y=draw, z=win
		int score=0;//rock=1,paper=2,scissors=3
		for(String[] s : input) {
			if(s[0].equals("A")) {//rock
				if(s[1].equals("X"))//lose
					score+= 3;//scissors=3
				else if(s[1].equals("Y"))//draw
					score+= 4;//rock=1
				else if(s[1].equals("Z"))//win
					score+= 8;//paper=2
			}
			else if(s[0].equals("B")) {//paper
				if(s[1].equals("X"))//lose
					score+= 1;//rock=1
				else if(s[1].equals("Y"))//draw
					score+= 5;//paper=2
				else if(s[1].equals("Z"))//win
					score+= 9;//scissors=3
			}
			else if(s[0].equals("C")) {//scissors
				if(s[1].equals("X"))//lose
					score+= 2;//paper=2
				else if(s[1].equals("Y"))//draw
					score+= 6;//scissors
				else if(s[1].equals("Z"))//win
					score+= 7;//rock=1
			}
		}
		System.out.println(score);
	}
	public static void part1(List<String[]> input) {
		int score=0;
		for(String[] s : input) {
			if(s[0].equals("A")) {//rock
				if(s[1].equals("X"))
					score+= 4;//x=1, draw=3
				else if(s[1].equals("Y"))
					score+= 8;//Y=2, win=6
				else if(s[1].equals("Z"))
					score+= 3;//z=3, lose=0
			}
			else if(s[0].equals("B")) {//paper
				if(s[1].equals("X"))
					score+= 1;//x=1, lose=0
				else if(s[1].equals("Y"))
					score+= 5;//Y=2, draw=3
				else if(s[1].equals("Z"))
					score+= 9;//z=3, win=6
			}
			else if(s[0].equals("C")) {
				if(s[1].equals("X"))
					score+= 7;//x=1, win=6
				else if(s[1].equals("Y"))
					score+= 2;//Y=2, lose=0
				else if(s[1].equals("Z"))
					score+= 6;//z=3, draw=3
			}
				
		}
		System.out.println(score);
	}
	public static List<String[]> getinput(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("C:\\Users\\USER\\eclipse-workspace\\advent2022\\src\\inputs\\"+filename+".txt"));
		List<String[]> list = new ArrayList<String[]>();
		String data;
		while (reader.hasNextLine()) {
			data = reader.nextLine();
	        list.add(data.split("\s"));
		}
		return list;
	}
