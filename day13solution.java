import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class day13solution {
	public static void main(String args[]){
		List<Point> Coords = new LinkedList<Point>();
		List<String[]> foldXY = new LinkedList<String[]>();
		String s=""; String[] point,fold;
		File inputFile = new File("/day13_input.txt");
		Scanner scan;
		try {
			scan = new Scanner(inputFile);
			while(scan.hasNext()) {
				s = scan.nextLine();
				if(s.contains(",")) {
					point = s.split(",");
					Coords.add(new Point(Integer.valueOf(point[0]),Integer.valueOf(point[1])));
				}
				else if(s.length() > 0) {
					foldXY.add(s.substring(11).split("="));
				}
			}
			System.out.println("part 1 solution: " + fold(Coords, foldXY, 1) );
			System.out.println(fold(Coords, foldXY, 2));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static int fold(List<Point> Coords, List<String[]> foldXY, int part) {
		List<Point> foldCoords = new LinkedList<Point>();		
		int	f;String type="";
		for(int i = 0; i< foldXY.size(); i++) {
			f = Integer.valueOf(foldXY.get(i)[1]);//gets the number that we folding by
			type = foldXY.get(i)[0];//gets the type x or y
			HashMap<Integer,Integer> fold = getFold(f);
			
			for(Point p : Coords) {
				if(type.equals("y")) {
					if(p.getY() > f) {
						p.setY(fold.get(p.getY()));
					}
					if(!checkForCoords(p, foldCoords) && p.getY() != f) {
						foldCoords.add(p);
					}
				}
				else {
					if(p.getX() > f) {
						p.setX(fold.get(p.getX()));
					}
					if(!checkForCoords(p, foldCoords) && p.getX() != f) {
						foldCoords.add(p);
					}
				
				}
			}
			if(part == 1) {
				return foldCoords.size();
			}
			Coords = foldCoords.stream()
					  .collect(Collectors.toList());
			System.out.println(foldCoords.size() + " new size after a fold when it is "+ f);
			foldCoords = new LinkedList<Point>();
		}
		printMap(Coords);
		System.out.println();
		return foldCoords.size();
	}
	public static boolean checkForCoords(Point p, List<Point> points) {
		for(Point pnt : points) {
			if(p.getX() == pnt.getX() && p.getY() == pnt.getY())
				return true;
		}
		return false;
	}
	public static HashMap<Integer, Integer> getFold(int fold){
		HashMap<Integer, Integer> newFold = new HashMap<Integer, Integer>();
		for(int i = 1; i <= fold;i++) {
			newFold.put(fold+i,fold-i);
		}
		return newFold;
	}
	public static void printMap(List<Point> Coords) {
		int maxX=0, maxY=0;
		for(Point p : Coords) {
			maxX = Math.max(maxX, p.getX());
			maxY = Math.max(maxY, p.getY());
		}
		int[][] graph = new int[maxX+1][maxY+1];
		for(Point p : Coords) {
			graph[p.getX()][p.getY()] = 1;
		}
		for(int j=0; j< graph[0].length; j++) {
			System.out.println();
			for(int i=0; i< graph.length; i++) {
				if(graph[i][j] == 1) {
					System.out.print("#");
				}
				else
					System.out.print(".");
			}
		}
	}
}

//class point which holds the coordinates (x,y)
public class Point {
	private int x,y;
	Point(int x1, int y1){
		this.x=x1;
		this.y=y1;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
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

}
