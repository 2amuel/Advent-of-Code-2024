import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day6Part2{

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

	int numberOfObstructions = countObstructions(theMap);

	System.out.println(numberOfObstructions);
    }

    public static int countObstructions(int[][] map){
	int count = 0;
	for(int i = 0; i < map.length; i++){
	    for(int j = 0; j < map[i].length; j++){
		if(map[i][j] == 0 && checkObstruction(map, i, j))
		    count++;
	    }
	}
	return count;
    }

    public static boolean checkObstruction(int[][] theMap, int i, int j){
	int[][] map = returnThe2dArray(theMap);	
	int positionY = checkY(map);
	int positionX = checkX(map[positionY]);
	map[i][j] = 1;
	int direction = 3;
	map[positionY][positionX] = 3;
	while(true){

	    //Up (3)
	    if(direction == 3){
                if(positionY == 0)
                    return false;
		if(map[positionY-1][positionX] != 0 && map[positionY-1][positionX] % 3 == 0)
		    return true;
		if(map[positionY-1][positionX] == 1)
		    direction = 4;
		else if(map[positionY-1][positionX] == 0){
		    map[positionY-1][positionX] = 3;
		    positionY--;
		}
		else{
		    map[positionY-1][positionX] *= 3;
                    positionY--;
		}
	    }
            //Right (4)
            if(direction == 4){
                if(positionX == map[0].length-1)
                    return false;
                if(map[positionY][positionX+1] != 0 && map[positionY][positionX+1] % 4 == 0)
                    return true;
                if(map[positionY][positionX+1] == 1)
                    direction = 5;
                else if(map[positionY][positionX+1] == 0){
                    map[positionY][positionX+1] = 4;
                    positionX++;
                }
                else{       
                    map[positionY][positionX+1] *= 4;
                    positionX++;
                }
            }
	    //Down (5)
            if(direction == 5){
		if(positionY == map.length-1)
                    return false;
                if(map[positionY+1][positionX] != 0 && map[positionY+1][positionX] % 5 == 0)
                    return true;
                if(map[positionY+1][positionX] == 1)
                    direction = 7;
                else if(map[positionY+1][positionX] == 0){
                    map[positionY+1][positionX] = 5;
                    positionY++;
                }
                else{
                    map[positionY+1][positionX] *= 5;
                    positionY++; 
                }
            }
	    //Left (7)
            if(direction == 7){
		if(positionX == 0)
                    return false;
                if(map[positionY][positionX-1] != 0 && map[positionY][positionX-1] % 7 == 0)
                    return true;
                if(map[positionY][positionX-1] == 1)
                    direction = 3;
                else if(map[positionY][positionX-1] == 0){
                    map[positionY][positionX-1] = 7;
                    positionX--;
                }
                else{
                    map[positionY][positionX-1] *= 7;
                    positionX--; 
                }
            }

	}
    }

    public static int[][] returnThe2dArray(int[][] arr){
	int[][] result = new int[arr.length][arr[0].length];
	for(int i = 0; i < result.length; i++){
	    for(int j = 0; j < arr[i].length; j++)
		result[i][j] = arr[i][j];
	}

	return result;
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
