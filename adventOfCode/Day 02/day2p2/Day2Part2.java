import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day2Part2{

    public static void main(String[] args){
	Scanner input = null;
	File reports = new File("./reports.txt");
	try{
	    input = new Scanner(reports);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}
	int count = 0;
	String s;
	
	while(input.hasNext()){
	    s = input.nextLine();
	    if(checkSafety(arrayify(s)) || checkSafetyDampened(arrayify(s)))
		count++;
	}
	System.out.println(count);
    }

    public static boolean checkSafetyDampened(int[] arr){
	for(int i = 0; i < arr.length; i++){
	    if(checkSafety(removeElement(arr, i)))
		return true;
	}
	return false;
    }

    public static int[] removeElement(int[] arr, int n){
	int[] result = new int[arr.length-1];
	int skip = 0;
	for(int i = 0; i < result.length; i++){
	    if(i == n)
		skip = 1;
	    result[i] = arr[i+skip];
	}
	return result;
    }

    public static void printArray(int[] arr){
	System.out.print("[");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
	    if(i != arr.length-1)
		System.out.print(", ");
	}
            System.out.println("]");
    }

    public static int[] arrayify(String s){
	int i = 0;
	int[] arr = new int[0];
	while(true){
	    if(s.contains(" "))
    	        arr = addEntry(arr, Integer.parseInt(s.substring(0, s.indexOf(" "))));
	    else
		arr = addEntry(arr, Integer.parseInt(s));
	    if(s.contains(" "))
	        s = s.substring(s.indexOf(" ")+1);
	    else
		break;
	}
	return arr;
    }

    public static int[] addEntry(int[] arr, int n){
	int[] result = new int[arr.length+1];
	for(int i = 0; i < arr.length; i++)
	    result[i] = arr[i];
	result[arr.length] = n;
	return result;
    }

    public static boolean checkSafety(int[] arr){
	if(arr.length<=1)
	    return true;
	if((arr[0] > arr[1] && !checkDecrease(arr)) || (arr[0] < arr[1]  && !checkIncrease(arr)))
	    return false;
	return checkDistance(arr);
    }

    public static boolean checkDistance(int[] arr){
	for(int i = 0; i < arr.length-1; i++){
	    if(Math.abs(arr[i]-arr[i+1])>3 || arr[i]==arr[i+1])
		return false;
	}
	return true;
    }

    public static boolean checkIncrease(int[] arr){
	for(int i = 0; i<arr.length-1; i++){
	    if(arr[i]>arr[i+1])
		return false;
	}
	return true;
    }

    public static boolean checkDecrease(int[] arr){
        for(int i = 0; i<arr.length-1; i++){
            if(arr[i]<arr[i+1])
                return false;
        }   
        return true;
    }

}
