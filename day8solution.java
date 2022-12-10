package advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day8 {
	public static int length = 99;
	public static void main(String[] args) throws FileNotFoundException {
		List<String> input = getinput("day8input");
		part1(input);
		part2(input);
	}
	public static void part1(List<String> input) {
		int[][] grid = getGrid(input);
		var cnt=0;
		for(var j=0; j<grid[0].length;j++) {
			for(var i=0; i<grid.length;i++) {
				cnt += checkSight(i,j, grid);
			}
		}
		System.out.println(cnt);
	}
	public static void part2(List<String> input) {
		int[][] grid = getGrid(input);
		var max=0;
		for(var j=0; j<grid[0].length;j++) {
			for(var i=0; i<grid.length;i++) {
				max = Math.max(max,checkScore(i,j, grid));
			}
		}
		System.out.println(max);
	}
	public static int checkScore(int i, int j, int[][] Grid) {
		int l = 1,r=1,u=1,d=1;//check right
		while(i+r < Grid[0].length && Grid[i][j] > Grid[i+r][j]) {
			if(i+r == Grid[0].length-1)
				break;
			r++;
		}
		while(i-l >= 0 && Grid[i][j] > Grid[i-l][j]) {//left
			if(i-l == 0)
				break;
			l++;
		}
//		down
		while(j+d < Grid.length && Grid[i][j] > Grid[i][j+d]) {
			if(j+d == Grid.length-1)
				break;
			d++;
		}
		//up
		while(j-u >= 0 && Grid[i][j] > Grid[i][j-u]) {
			if(j-u == 0)
				break;
			u++;
		}
		return l*r*u*d;
	}
	public static int checkSight(int i, int j, int[][] Grid) {
		if(i == 0 || j==0 || i == Grid[0].length-1 || j == Grid[0].length -1)
			return 1;
		var cnt = 1;//check right
		while(i+cnt < Grid[0].length && Grid[i][j] > Grid[i+cnt][j]) {
			if(i+cnt == Grid[0].length-1)
				return 1;
			cnt++;
		}
		cnt = 1;
		while(i-cnt >= 0 && Grid[i][j] > Grid[i-cnt][j]) {//left
			if(i-cnt == 0)
				return 1;
			cnt++;
		}
//		down
		cnt =1;
		while(j+cnt < Grid.length && Grid[i][j] > Grid[i][j+cnt]) {
			if(j+cnt == Grid.length-1)
				return 1;
			cnt++;
		}
		//up
		cnt =1;
		while(j-cnt >= 0 && Grid[i][j] > Grid[i][j-cnt]) {
			if(j-cnt == 0)
				return 1;
			cnt++;
		}
		return 0;
	}
	public static int[][] getGrid(List<String> input){
		int[][] Grid = new int[input.get(0).length()][length];
		var cnt=0;
		for(String line : input) {
			for(int i=0; i<line.length();i++) {
				Grid[i][cnt] = line.charAt(i);
			}
			cnt++;
		}
		return Grid;
	}
	public static List<String> getinput(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("C:\\Users\\USER\\eclipse-workspace\\advent2022\\src\\inputs\\"+filename+".txt"));
		List<String> list = new ArrayList<String>();
		while (reader.hasNextLine())
	        list.add(reader.nextLine());
		return list;
	}

}
