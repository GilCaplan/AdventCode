import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class day11solution {
	public static int cnt=0, row =10,col=10;
	public static boolean part1 = false;
	public static void main(String args[]) throws FileNotFoundException{
		File inputFile = new File("/day11_input.txt");
		List<String> input = new LinkedList<String>();
		Scanner scan = new Scanner(inputFile); 
		while(scan.hasNext()) {
			input.add(scan.nextLine());
		}
		scan.close();
		part(input, 1);
		part(input, 2);
		
	}
	public static int part(List<String> input, int part) {
		Spot11[][] graph = new Spot11[col][row];
		int col=0;
		for(String s : input) {
			for(int i = 0; i < s.length();i++) {
				graph[i][col] = new Spot11(Integer.valueOf(String.valueOf(s.charAt(i))));
			}
			col++;
		}
		int steps=0;
		if(part == 1) {
			steps = 100;
			for(int i=0 ; i< steps;i++) {
				graph = Step(graph);
			}
			System.out.println("solution to part1 is: " + cnt);
			return -1;
		}
		
		while(!checkSync(graph)) {
			graph = Step(graph);
			steps++;
		}
		System.out.println("solution to part 2 is: " + steps);
		return -1;
	}
	public static boolean checkSync(Spot11[][] graph) {
		for(int j = 0; j < graph[0].length; j++) {//row
			for(int i = 0; i < graph.length; i++) {//col
				if(graph[j][i].getValue() != 0)
					return false;
			}
		}
		return true;
	}
	public static Spot11[][] Step(Spot11[][] graph) {
		for(int j = 0; j < graph[0].length; j++) {//row
			for(int i = 0; i < graph.length; i++) {//col
				graph[i][j].setValue();
				graph[i][j].setFlashed(false);
			}
		}
		graph = getFlashes(graph);
		
		for(int j = 0; j < graph[0].length; j++) {//row
			for(int i = 0; i < graph.length; i++) {//col
				if(graph[j][i].getValue() > 9) {
					graph[j][i].set0();
				}
			}
		}
		return graph;
	}
	public static Spot11[][] getFlashes(Spot11[][] graph){
		for(int j = 0; j < graph[0].length; j++) {//row
			for(int i = 0; i < graph.length; i++) {//col
				if(graph[j][i].getValue() > 9 && !graph[j][i].isFlashed()) {
					graph[j][i].setFlashed(true);
					cnt++;
					graph = addToNeighbor(j, i, graph);
					graph = getFlashes(graph);
					
				}
			}
		}
		return graph;
	}
	public static void printGraph(Spot11[][] graph) {
		System.out.println();
		for(int j = 0; j < graph[0].length; j++) {//row
			for(int i = 0; i < graph.length; i++) {//col
				System.out.print(graph[i][j].getValue() + ", ");
			}
			System.out.println();
		}
	}
	 public static Spot11[][] addToNeighbor(int r, int c, Spot11[][] graph) { 
		 // row = 0 col = 1
		int upRow,downRow,leftCol,rightCol;
        upRow = r > 0 ? r - 1 : r;//0
        downRow = r < row - 1 ? r + 1 : r;//2
        leftCol = c > 0 ? c - 1 : c;//0
        rightCol = c < col - 1 ? c + 1 : c;//1
        for (int i = upRow; i < downRow + 1; i++) {//0-2
            for (int j = leftCol; j < rightCol + 1; j++) {//0-1
            	//0,0  0,1  1,0  1,1  2,0  2,1
                if ((r == i && c == j) == false && graph[i][j].getValue() > 0) {
                    graph[i][j].setValue();
                }
            }
        }
        return graph;
 	}
}
