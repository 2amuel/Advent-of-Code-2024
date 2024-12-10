import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day10Part1{

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

    public static int scoreCalc(int[][] map, int i1, int j1){
	int score = 0;
	for(int i2 = 0; i2 < map.length; i2++){
	    for(int j2 = 0; j2 < map[i2].length; j2++){
		if(map[i2][j2] == 9 && hasPath(map, i1, j1, i2, j2))
		    score++;
	    }
	}
	return score;
    }

    public static boolean hasPath(int[][] map, int i1, int j1, int i2, int j2){
	if(map[i1][j1] == 9)
	    return i1 == i2 && j1 == j2;
	int next = map[i1][j1] + 1;
	return (i1 != 0 && map[i1-1][j1] == next && hasPath(map, i1-1, j1, i2, j2)) || (i1 != map.length-1 && map[i1+1][j1] == next && hasPath(map, i1+1, j1, i2, j2)) || (j1 != 0 && map[i1][j1-1] == next && hasPath(map, i1, j1-1, i2, j2)) || (j1 != map[i1].length-1 && map[i1][j1+1] == next && hasPath(map, i1, j1+1, i2, j2));
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
