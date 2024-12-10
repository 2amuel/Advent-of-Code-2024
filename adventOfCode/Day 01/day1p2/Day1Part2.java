import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day1Part2{
    public static void main(String[] args){
	
	File lists = new File("./lists.txt");
        Scanner input = null;
	try{
	    input = new Scanner(lists);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
        }
	String s = "";
        int difference = 0;
	int [] arr1 = new int[0];
	int [] arr2 = new int[0];
       	while(input.hasNext()){
            s = input.nextLine();
	    arr1 = addEntry(arr1, Integer.parseInt(s.substring(0,5)));
	    arr2 = addEntry(arr2, Integer.parseInt(s.substring(8)));
	}

	System.out.println(similarity(arr1, arr2));

    }

    public static int similarity(int[] arr1, int[] arr2){
	int result = 0;
	for(int i = 0; i < arr1.length; i++)
	    result += arr1[i]*howMany(arr2, arr1[i]);
	return result;
    }


    public static int[] addEntry(int[] arr, int n){
	int[] result = new int[arr.length+1];
	for(int i = 0; i < arr.length; i++)
	    result[i] = arr[i];
	result[arr.length] = n;
	return result;
    }

    public static int howMany(int[] arr1, int n){
	int count = 0;
	for(int i = 0; i < arr1.length; i++){
	    if(arr1[i] == n)
		count++;
	}
	return count;
    }

}
