import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day10Part2{

    public static void main(String[] args){
	Scanner input = null;

	File mapFile = new File("./map.txt");

	try{
	    input = new Scanner(mapFile);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}

	int[][] map = makeMap(input);

	int scores = calcScoreTotal(map);

	System.out.println(scores);

    }

    public static int calcScoreTotal(int[][] map){
	int total = 0;
	for(int i = 0; i < map.length; i++){
	    for(int j = 0; j < map[i].length; j++){
		if(map[i][j] == 0)
		    total += scoreCalc(map, i, j);
	    }
	}
	return total;
    }

    public static int scoreCalc(int[][] map, int i, int j){
	if(map[i][j] == 9)
	    return 1;
	int total = 0;
	int next = map[i][j] + 1;
	if(i != 0 && map[i-1][j] == next)
	    total += scoreCalc(map, i-1, j);
        if(i != map.length-1 && map[i+1][j] == next)
            total += scoreCalc(map, i+1, j);
        if(j != 0 && map[i][j-1] == next)
            total += scoreCalc(map, i, j-1);
        if(j != map[i].length-1 && map[i][j+1] == next)
            total += scoreCalc(map, i, j+1);
	return total;
    }

    public static int[][] makeMap(Scanner input){
	String s;
	int[][] result = new int[0][0];
	while(input.hasNext()){
	    s = input.nextLine();
	    result = addRow(result, s);
	}
	return result;
    }

    public static int[][] addRow(int[][] arr, String s){
	int[][] result = new int[arr.length+1][s.length()];
	for(int i = 0; i < arr.length; i++){
	    result[i] = arr[i];
	}
	result[arr.length] = arrayify(s);
	return result;
    }

    public static int[] arrayify(String s){
	int[] result = new int[s.length()];
	for(int i = 0; i < s.length(); i++){
	    result[i] = Integer.parseInt(s.substring(i, i+1));
	}
	return result;
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
