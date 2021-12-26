public static void main(String args[]){       
		String s="";
		File inputFile = new File("/day17_input.txt/");
		Scanner scan;int[] range = new int[4];
		String[] ranges, rx,ry;
		try {
			scan = new Scanner(inputFile);
			s = scan.nextLine();
			ranges = s.split("target area: ")[1].split(",");
			rx = ranges[0].split("x=")[1].split("\\..");
			ry = ranges[1].split("y=")[1].split("\\..");
			range[0] = Integer.valueOf(rx[0]);
			range[1] = Integer.valueOf(rx[1]);
			range[2] = Integer.valueOf(ry[0]);
			range[3] = Integer.valueOf(ry[1]);
			System.out.println(part1(range));
			System.out.println(part2(range));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int part1(int[] ranges) {
        int max = 0;
        for (int j = 0; j < 1000; j++) {
            for (int i = 1; i < 1000; i++) {
            	max = Math.max(max, moveStep(i,j, ranges));
            }
        }
		return max;
    }
	public static int part2(int[] ranges) {
        int cnt = 0;
        for (int j = 0; j < 400; j++) {
            for (int i = -100; i < 100; i++) {
            	if (moveStep(i,j, ranges) != -1)
            		cnt++;
            }
        }
		return cnt;
    }

    private static int moveStep(int yv, int xv, int[] range) {
        int x_min = range[0], x_max = range[1], y_min = range[2],y_max = range[3];
        boolean check = false;
        int yheight = 0,x=0,y=0;
        while(true) {
            x += xv;
            y += yv;
            
            if (x > x_max || y < y_min) 
            	return -1;//if out of range then we don't count it
            
            yv--; //due to drag
            yheight = Math.max(yheight, y);
            if (xv > 0) 
            	xv--;
           
            if (x >= x_min && x <= x_max && y >= y_min && y <= y_max) {
                check = true;//checking that
                break;
            }
            
        }
        if (!check) 
        	return -1;
        return yheight;
    }
	
}
