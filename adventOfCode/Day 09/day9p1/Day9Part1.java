import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day9Part1{

    public static void main(String[] args){
	Scanner input = null;

	File disk = new File("./diskmap.txt");

	try{
	    input = new Scanner(disk);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}

	long[] diskArr = makeDiskArray(input.nextLine());

	long[] expandedDisk = makeExpandedDisk(diskArr);

	sortArray(expandedDisk);

        printArray(expandedDisk);

	long checksum = findChecksum(expandedDisk);

	System.out.println(checksum);
    }

    public static void sortArray(long[] arr){
	for(int i = arr.length-1; i >= 0; i--){
	    if(arr[i] != -1 && i > arrayIndexOf(arr, -1))
		switchElements(arr, i, arrayIndexOf(arr, -1));
	}
    }

    public static void switchElements(long[] arr, int i1, int i2){
	long temp = arr[i1];
	arr[i1] = arr[i2];
	arr[i2] = temp;
    }

    public static int arrayIndexOf(long[] arr, int n){
	for(int i = 0; i < arr.length; i++){
	    if(arr[i] == n)
		return i;
	}
	return -1;
    }

    public static long findChecksum(long[] arr){
	int total = 0;
	for(int i = 0; i < arr.length; i++){
	    if(arr[i] == -1)
		break;
	    total += i*arr[i];
	}
	return total;
    }

    public static void printArray(long[] arr){
	System.out.print("[");
	for(int i = 0; i < arr.length; i++){
	    System.out.print(arr[i]);
	    if(i != arr.length-1)
		System.out.print(", ");
	}
	System.out.println("]");
    }

    public static long[] makeExpandedDisk(long[] arr){
	long[] result = new long[0];
	for(int i = 0; i < arr.length; i++){
	    for(int j = 0; j < arr[i]; j++){
		if(i % 2 == 0)
		    result = addElement(result, i/2);
		else
		    result = addElement(result, -1);
	    }
	}
	return result;
    }

    public static long[] addElement(long[] arr, int n){
	long[] result = new long[arr.length+1];
	for(int i = 0; i < arr.length; i++)
	    result[i] = arr[i];
	result[arr.length] = n;
	return result;
    }

    public static long[] makeDiskArray(String s){
	long[] result = new long[s.length()];
	for(int i = 0; i < result.length; i++)
	    result[i] = Long.parseLong(s.substring(i, i+1));
	return result;
    }

}
