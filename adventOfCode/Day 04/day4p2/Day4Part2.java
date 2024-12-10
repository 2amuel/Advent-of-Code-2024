import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day4Part2{

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
	for(int i = 1; i < arr.length-1; i++){
	    for(int j = 1; j < arr[0].length()-1; j++){
		count += checkXmas(arr,i,j);
	    }
	}
	return count;
    }

    public static int checkXmas(String[] arr, int i, int j){

	int count = 0;

	if( arr[i].charAt(j) == 'A' && ( (arr[i-1].charAt(j-1) == 'M' && arr[i+1].charAt(j+1) == 'S') || (arr[i-1].charAt(j-1) == 'S' && arr[i+1].charAt(j+1) == 'M') ) && ( (arr[i-1].charAt(j+1) == 'M' && arr[i+1].charAt(j-1) == 'S') || (arr[i-1].charAt(j+1) == 'S' && arr[i+1].charAt(j-1) == 'M') ) )
	    count++;
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
