public class day12solution {
	public static HashMap<String, List<String>> connections = new HashMap<String, List<String>>();
	
	public static void main(String args[]){
		List<String> input = new ArrayList<String>();
		String s="";
		File inputFile = new File("/day12_input.txt");
		Scanner scan;
		try {
			scan = new Scanner(inputFile);
			while(scan.hasNext()) {
				s = scan.nextLine();
				input.add(s);
			}
			System.out.println("input received");
			solution(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void solution(List<String> input) {
		String[] path;
		List<String> close;
		for(String s : input) {
			close = new LinkedList<String>();
			path = s.split("-");
			for(int i =0; i< 2;i++) {
				if(!connections.containsKey(path[i]))
					connections.put(path[i], new LinkedList<>());
				if(!path[1-i].equals("start"))
					connections.get(path[i]).add(path[1-i]);
			}
			
		}
		for(String c : connections.keySet()) {
			System.out.println(c + " : "+ connections.get(c));
		}
		System.out.println(search("start", new LinkedList<String>(), false));

	}

	}
	public static int searchp1(String current, List<String> lowerUsed) {
		if(current.equals("end"))
			return 1;
		int cnt = 0;
		for(String next : connections.get(current)) {
			if(!lowerUsed.contains(next)) {
				if(isLowerCase(next)) {
					lowerUsed.add(next);
					cnt += searchp1(next, lowerUsed);
					lowerUsed.remove(next);
				}
				else {
					cnt += searchp1(next, lowerUsed);
				}
			}
		}
		return cnt;
	}
	
	
	private static boolean isLowerCase(String str){
        char[] charArray = str.toCharArray();
        for(int i=0; i < charArray.length; i++){
            if( !Character.isLowerCase( charArray[i] ))
                return false;
        }
        return true;
	}
}
