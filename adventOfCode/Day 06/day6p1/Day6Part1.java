import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day6Part1{

    public static void main(String[] args){
	Scanner input = null;

	File map = new File("./map.txt");

	try{
	    input = new Scanner(map);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}

	int[][] theMap = makeMap(input);

	int steps = countSteps(theMap);

	System.out.println(steps);
    }

    public static int countSteps(int[][] map){
	int direction = 0;
	int positionY = checkY(map);
	int positionX = checkX(map[positionY]);
	while(true){
	    if((direction == 0 && positionY == 0) || (direction == 2 && positionY == map.length-1) || (direction == 1 && positionX == map.length-1) || (direction == 3 && positionX == 0)){
		map[positionY][positionX] = 3;
		break;
	    }
	    //Direction is up
	    if(direction == 0){
		if(map[positionY-1][positionX] == 1){
		    direction = 1;
		}
		else{
		    map[positionY-1][positionX] = 2;
		    map[positionY][positionX] = 3;
		    positionY--;
		}
	    }
            //Direction is right
            if(direction == 1){
                if(map[positionY][positionX+1] == 1){
                    direction = 2;
                }                                   
                else{                               
                    map[positionY][positionX+1] = 2;
                    map[positionY][positionX] = 3;
                    positionX++;  
                }
            }
            //Direction is down
            if(direction == 2){
                if(map[positionY+1][positionX] == 1){
                    direction = 3;
                }
                else{
                    map[positionY+1][positionX] = 2;
                    map[positionY][positionX] = 3;
                    positionY++;
                }
            }
            //Direction is left
            if(direction == 3){
                if(map[positionY][positionX-1] == 1){
                    direction = 0;
                }
                else{
                    map[positionY][positionX-1] = 2;
                    map[positionY][positionX] = 3;
                    positionX--; 
                }
            }
	}

	return countThe3(map);

    }

    public static int countThe3(int[][] map){
	int count = 0;
	for(int i = 0; i < map.length; i++){
	    for(int j = 0; j < map[i].length; j++){
		if(map[i][j] == 3)
		    count++;
	    }
	}
	return count;
    }

    public static int checkX(int[] arr){

	for(int i = 0; i < arr.length; i++){
	    if(arr[i]==2)
		return i;
	}
	return -1;
    }

    public static int checkY(int[][] map){

	for(int i = 0; i < map.length; i++){
	    if(containsIt(map[i]))
		return i;
	}
	return -1;
    }

    public static boolean containsIt(int[] arr){

	for(int i = 0; i < arr.length; i++){
	    if(arr[i] == 2)
		return true;
	}
	return false;
    }

    public static void print2dArray(int[][] arr){
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
	    if(s.charAt(i) == '.')
		result[i] = 0;
	    else if(s.charAt(i) == '#')
		result[i] = 1;
	    else
		result[i] = 2;
	}
	return result;
    }

}
