import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day7 {
	public static void main(String[] args) throws FileNotFoundException {
		List<String[]> input = getinput("day7input");
		var parent = new Dir(null);
		var currentDir = parent;
		for(int i=0; i < input.size(); i++) {//commands $: cd\ls
			var command = input.get(i);
			if(command.length > 1 && command[0].equals("$")){
				if(command[1].equals("cd")){//go back one
					if(command[2].equals("..")) //move out a directory
						currentDir = currentDir.getParent();
					else {
						var newDir = new Dir(currentDir);
						currentDir.addChild(newDir);//add the child Directory
						currentDir = newDir;//set it to the new directory
					}
				}
				if(command[1].equals("ls")){//find current directory
					while(i+1 < input.size() && !input.get(i+1)[0].equals("$")) {
						i++;
						command = input.get(i);//get's line from commands
						if(command[0].equals("dir")) {
							continue;//ignore, not relevant. can't use cd on it.
						}
						currentDir.addFile(Integer.parseInt(command[0]));//file name, size
					}
				}
			}
		}
		var sumDir = new ArrayList<Integer>();
		suminDirectories(parent, sumDir);
		part1(sumDir);
		part2(sumDir);
	}
	public static void part1(List<Integer> sumDir) {
		int totalSum = 0;
		for(int sum : sumDir) 
			if(sum <= 100000) 
				totalSum += sum;
		System.out.println(totalSum);
	}
	public static void part2(List<Integer> sumDir) {
		var Space = 70000000 - sumDir.get(sumDir.size()-1);//Space in the system
		var min = 70000000;//set high since we want to find the minimal amount
		for(var sum : sumDir)
			if(Space + sum > 30000000)
				min = Math.min(min, sum);
		System.out.println(min);
	}
	public static int suminDirectories(Dir dir, List<Integer> sumDir){
		var sum = 0;
		for (var file: dir.getFiles()) 
			sum += file;
		for (var child: dir.getDirectories()) 
			sum += suminDirectories(child, sumDir);
		sumDir.add(sum);
		return sum;
	}
	public static List<String[]> getinput(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("C:\\Users\\USER\\eclipse-workspace\\advent2022\\src\\inputs\\"+filename+".txt"));
		List<String[]> list = new ArrayList<String[]>();
		while (reader.hasNextLine())
			list.add(reader.nextLine().split(" "));
		return list;
	}
}


//class/object Dir

import java.util.ArrayList;
import java.util.List;

public class Dir {
	private final List<Integer> files;
	private final List<Dir> directories;
	private final Dir parent;

	public Dir(Dir parent) {
		this.parent = parent;
		this.files = new ArrayList<>();
		this.directories = new ArrayList<>();
	}
	public void addChild(Dir child) {
		directories.add(child);
	}
	public List<Dir> getDirectories() {
		return directories;
	}
	public List<Integer> getFiles() {
		return files;
	}
	public void addFile(Integer f) {
		this.files.add(f);
	}
	public Dir getParent() {
		return parent;
	}
}



