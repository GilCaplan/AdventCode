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
			if(connections.get(path[0]) != null) {
				close = connections.get(path[0]);
				if(!path[1].equals("start"))
					close.add(path[1]);
			}
			else {
				if(!path[1].equals("start"))
					close.add(path[1]);
			}
			connections.put(path[0],close);
			
			close = new LinkedList<String>();
			if(connections.get(path[1]) != null) {
				close = connections.get(path[1]);
				if(!path[0].equals("start"))
					close.add(path[0]);
			}
			else {
				if(!path[0].equals("start"))
					close.add(path[0]);
			}
			connections.put(path[1],close);
		}
		for(String c : connections.keySet()) {
			System.out.println(c + " : "+ connections.get(c));
		}
//		System.out.println(searchp1("start", new LinkedList<String>()));
		System.out.println(searchp2("start", new LinkedList<String>()));

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
