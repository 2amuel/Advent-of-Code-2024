import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day12Part1{

    public static void main(String[] args){
	Scanner input = null;

	File farm = new File("./farm.txt");

	try{
	    input = new Scanner(farm);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}

	String[][] map = makeMap(input);

	int fencePrice = calcPrice(map);

	System.out.println(fencePrice);
    }

    public static int calcPrice(String[][] map){
	int total = 0;
	for(int i = 0; i < map.length; i++){
	    for(int j = 0; j < map[i].length; j++){
		if(!map[i][j].equals(".")){
		    int[] areaAndPerimeter = calcAreaAndPerimeter(map, i, j);
		    total += areaAndPerimeter[0]*areaAndPerimeter[1];
		    commaToPeriod(map);
		}
	    }
	}
	return total;
    }

    public static int[] calcAreaAndPerimeter(String[][] map, int i, int j){
	String letter = map[i][j];
	map[i][j] = ",";
	int perimeter = 0;
	int area = 1;
	if(i == 0 || (!map[i-1][j].equals(letter) && !map[i-1][j].equals(",")))
	    perimeter++;
	else if(map[i-1][j].equals(letter)){
	    int[] temp = calcAreaAndPerimeter(map, i-1, j);
	    perimeter += temp[1];
	    area += temp[0];
	}
        if(i == map.length-1 || (!map[i+1][j].equals(letter) && !map[i+1][j].equals(",")))
            perimeter++;
        else if(map[i+1][j].equals(letter)){
            int[] temp = calcAreaAndPerimeter(map, i+1, j);
            perimeter += temp[1];
            area += temp[0];
        }
        if(j == 0 || (!map[i][j-1].equals(letter) && !map[i][j-1].equals(",")))
            perimeter++;
        else if(map[i][j-1].equals(letter)){
            int[] temp = calcAreaAndPerimeter(map, i, j-1);
            perimeter += temp[1];
            area += temp[0];
        }
        if(j == map[i].length-1 || (!map[i][j+1].equals(letter) && !map[i][j+1].equals(",")))
            perimeter++;
        else if(map[i][j+1].equals(letter)){
            int[] temp = calcAreaAndPerimeter(map, i, j+1);
            perimeter += temp[1];
            area += temp[0];
        }
	return new int[]{area, perimeter};
    }

    public static void commaToPeriod(String[][] map){
	for(int i = 0; i < map.length; i++){
	    for(int j = 0; j < map[i].length; j++){
		if(map[i][j].equals(","))
		    map[i][j] = ".";
	    }
	}
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

}
