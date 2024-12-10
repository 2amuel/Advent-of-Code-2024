import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day4Part1{

    public static void main(String[] args){
	Scanner input = null;

	File search = new File("./search.txt");

	try{
	    input = new Scanner(search);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}
	String[] arr = makeArr(input);
	printArray(arr);
	int count = howMany(arr);
	System.out.println(count);
    }

    public static void printArray(String[] arr){
	System.out.print("[");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
	    if(i != arr.length-1)
		System.out.print(", ");
	}
            System.out.println("]");
    }

    public static int howMany(String[] arr){
	int count = 0;
	for(int i = 0; i < arr.length; i++){
	    for(int j = 0; j < arr[0].length(); j++){
		count += checkXmas(arr,i,j);
	    }
	}
	return count;
    }

    public static int checkXmas(String[] arr, int i, int j){

	int count = 0;
	
	//Up
	if(i >= 3){
	    if(arr[i].charAt(j) == 'X' && arr[i-1].charAt(j) == 'M' && arr[i-2].charAt(j) == 'A' && arr[i-3].charAt(j) == 'S')
	    count++;
	}

	//Upright
	if(i >= 3 && j <= arr[0].length()-4){
            if(arr[i].charAt(j) == 'X' && arr[i-1].charAt(j+1) == 'M' && arr[i-2].charAt(j+2) == 'A' && arr[i-3].charAt(j+3) == 'S')
	    count++;
        }

        //Right
        if(j <=  arr[0].length()-4){
            if(arr[i].charAt(j) == 'X' && arr[i].charAt(j+1) == 'M' && arr[i].charAt(j+2) == 'A' && arr[i].charAt(j+3) == 'S')
            count++;
        }

	//Downright
        if(i <= arr.length - 4 && j <= arr[0].length()-4){
            if(arr[i].charAt(j) == 'X' && arr[i+1].charAt(j+1) == 'M' && arr[i+2].charAt(j+2) == 'A' && arr[i+3].charAt(j+3) == 'S')
            count++;
        }

	//Down
        if(i <= arr.length - 4){
            if(arr[i].charAt(j) == 'X' && arr[i+1].charAt(j) == 'M' && arr[i+2].charAt(j) == 'A' && arr[i+3].charAt(j) == 'S')
            count++;
        }

	//Downleft     
        if(i <= arr.length - 4 && j >= 3){                          
            if(arr[i].charAt(j) == 'X' && arr[i+1].charAt(j-1) == 'M' && arr[i+2].charAt(j-2) == 'A' && arr[i+3].charAt(j-3) == 'S')
            count++;
        }

	//Left
        if(j >= 3){
            if(arr[i].charAt(j) == 'X' && arr[i].charAt(j-1) == 'M' && arr[i].charAt(j-2) == 'A' && arr[i].charAt(j-3) == 'S')
            count++;
        }

	//Upleft
        if(i>= 3 && j >= 3){
            if(arr[i].charAt(j) == 'X' && arr[i-1].charAt(j-1) == 'M' && arr[i-2].charAt(j-2) == 'A' && arr[i-3].charAt(j-3) == 'S')
            count++;
        }

	return count;

    }

    public static String[] makeArr(Scanner input){
	String s;
	String[] arr = new String[0];
	while(input.hasNext()){
	    s = input.nextLine();
	    arr = addEntry(arr, s);
	}

	return arr;
    }

    public static String[] addEntry(String[] arr, String s){
	String[] result = new String[arr.length+1];
	for(int i = 0; i < arr.length; i++)
	    result[i] = arr[i];
	result[arr.length] = s;
	return result;
    }

}
