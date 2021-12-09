import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class day9solution {
	public static void main(String args[]){
		String s="";
		File inputFile = new File("/day9_input.txt");
		Scanner scan;
		List<List<Spot>> graph = new LinkedList<List<Spot>>();
		List<Spot> row = new LinkedList<Spot>();int y=0;
		try {
			scan = new Scanner(inputFile);
			while(scan.hasNext()) {
				s = String.valueOf(scan.nextLine());
				for(int x=0; x< s.length(); x++) {
					row.add(new Spot(x,y,s.charAt(x)));
				}
				graph.add(row);
				row = new LinkedList<Spot>();
				y++;
			}
			System.out.println("input received");
			System.out.println(part1(graph));
			System.out.println(part2(graph));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static int part1(List<List<Spot>> graph) {
		int sum=0;
		for(List<Spot> row : graph) {
			for(Spot s: row) {
				sum+= s.getLowPoints(graph);
			}
		}
		return sum;
	}
	public static int part2(List<List<Spot>> graph) {
		List<Integer> basinsSum = new LinkedList<Integer>();
		List<Spot> lowPoints = new LinkedList<Spot>();
		for(List<Spot> row : graph) {
			for(Spot s: row) {
				 if (s.getLowPoints(graph) == 0)
					 lowPoints.add(s);
			}
		}int num =0;
		for(Spot s : lowPoints) {//gets all the basins size
			num =s.getBasin(s.getX(), s.getY(), graph);
			basinsSum.add(num);
		}
		Collections.sort(basinsSum, Collections.reverseOrder());
		return basinsSum.get(0)*basinsSum.get(1)*basinsSum.get(2);//3 biggest basin sizes
	}
}


//class Spot that is used for solving the puzzle

import java.util.List;

public class Spot {
	private int x,y,value;
	Spot(int x1, int y1, char val){
		this.x = x1;
		this.y= y1;
		this.value = Integer.parseInt(String.valueOf(val));
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int v) {
		this.value = v;
	}
	public int getLowPoints(List<List<Spot>> graph) {
		if(this.value == 9)
			return 0;
		int cnt = 0;
		//check above, get the y above which is this.y-1 and the same x then compare the values
		if( this.y == 0 ||  this.value < graph.get(this.y - 1).get(this.x).getValue())
			cnt++;
		//check below
//		System.out.println(this.y + " " + graph.size()+ " "+ this.toString());
		if(this.y == graph.size() - 1 || this.value < graph.get(this.y + 1).get(this.x).getValue())
			cnt++;//10, 5
		//check to left
		if( this.x == 0 || this.value < graph.get(this.y).get(this.x - 1).getValue())
			cnt++;
		//check to right
		if( this.x >= graph.get(this.y).size() - 1 || this.value < graph.get(this.y).get(this.x + 1).getValue())
			cnt++;
		if(cnt == 4)
			return this.value + 1;
		return 0;
	}
	int getBasin(int row, int col, List<List<Spot>> graph) {
		if (col < 0 || row < 0 ||  col >= graph.get(0).size() || row >=  graph.size() || graph.get(row).get(col).getValue() == 9)
			return 0;//if its on a edge or corner or 9 then we stop
		graph.get(row).get(col).setValue(9);//so we stop and don't repeat same spots 
		return 1 + getBasin(row + 1, col, graph) + getBasin(row - 1, col, graph) + getBasin(row, col - 1, graph) + getBasin(row, col + 1, graph);//counts 1 plus continue recursively in each direction
	}

}


