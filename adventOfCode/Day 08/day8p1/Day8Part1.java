import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day8Part1{

    public static void main(String[] args){
	Scanner input = null;

	File mapFile = new File("./map.txt");

	try{
	    input = new Scanner(mapFile);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}

	String[][] map = makeMap(input);

	int count = countAntinodes(map);
	
	System.out.println(count);
    }

    public static int countAntinodes(String[][] map){
	int[][] antinodeMap = new int[map.length][map[0].length];
	String signal;
	for(int i = 0; i < map.length; i++){
	    for(int j = 0; j < map[i].length; j++){
		if(!map[i][j].equals("."))
		    addAntinodes(antinodeMap, map, i, j);
	    }
	}
	return howManyAntinodes(antinodeMap);
    }

    public static void addAntinodes(int[][] antinodeMap, String[][] map, int i, int j){
	String signal = map[i][j];
	for(int a = 0; a < map.length; a++){
	    for(int b = 0; b < map[a].length; b++){
		if(!(a == i && b == j) && map[a][b].equals(signal)){
		    if(i-a+i >= 0 && i-a+i < map.length && j-b+j >= 0 && j-b+j < map[0].length){
			antinodeMap[i-a+i][j-b+j] = 1;
		    }
		}
	    }
	}
    }

    public static int howManyAntinodes(int[][] map){
	int count = 0;
	for(int i = 0; i < map.length; i++){
	    for(int j = 0; j < map[i].length; j++){
		if(map[i][j] == 1)
		    count++;
	    }
	}
	return count;
    }

    public static String[][] makeMap(Scanner input){
	String s;
	String[][] result = new String[0][0];
	while(input.hasNext()){
	    s = input.nextLine();
	    result = addRow(result, s);
	}
	return result;
    }

    public static String[][] addRow(String[][] arr, String s){
	String[][] result = new String[arr.length+1][s.length()];
	for(int i = 0; i < arr.length; i++){
	    result[i] = arr[i];
	}
	result[arr.length] = arrayify(s);
	return result;
    }

    public static String[] arrayify(String s){
	String[] result = new String[s.length()];
	for(int i = 0; i < s.length(); i++){
	    result[i] = s.substring(i, i+1);
	}
	return result;
    }

    public static void print2dArray(String[][] arr){
	System.out.println("[");
	for(int i = 0; i < arr.length; i++){
	    System.out.print("[");
	    for(int j = 0; j < arr[i].length; j++){
		System.out.print(arr[i][j]);
		if(j != arr[i].length-1)
		    System.out.print(", ");
	    }
	    System.out.println("]");
	}
	System.out.println("]");
    }

    public static void print2dArrayInt(int[][] arr){
        System.out.println("[");
        for(int i = 0; i < arr.length; i++){
            System.out.print("[");
            for(int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j]);
                if(j != arr[i].length-1)
                    System.out.print(", ");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

}
