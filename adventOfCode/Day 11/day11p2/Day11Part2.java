import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Day11Part2{

    public static HashMap<String, Long> memoization = new HashMap<>();

    public static void main(String[] args){
	Scanner input = null;

	File stonesFile = new File("./stones.txt");

	try{
	    input = new Scanner(stonesFile);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}

	long[] stones = makeArray(input);
	
	long count = 0;

	for(int i = 0; i < stones.length; i++)
	    count += findLength(stones[i], 75);

	System.out.println(count);
    }

    public static long findLength(long stone, int blinks){
	if(blinks == 0)
	    return 1;
	if(memoization.containsKey(stone+" "+blinks))
	    return memoization.get(stone+" "+blinks);
	if(stone == 0){
	    long length = findLength(1, blinks-1);
	    memoization.put(stone+" "+blinks, length);
	    return length;
	}
	String s = ""+stone;
	if(s.length() % 2 == 0){
	    long length = findLength(Long.parseLong(s.substring(0, s.length()/2)), blinks-1) + findLength(Long.parseLong(s.substring(s.length()/2)), blinks-1);
	    memoization.put(stone+" "+blinks, length);
	    return length;
	}
	long length = findLength(stone*2024, blinks-1);
	memoization.put(stone+" "+blinks, length);
	return length;
    }

    public static long[] makeArray(Scanner input){
	String s = input.nextLine();
	long[] result = new long[0];
	while(s.contains(" ")){
	    result = addElement(result, Long.parseLong(s.substring(0,s.indexOf(" "))));
	    s = s.substring(s.indexOf(" ")+1);
	}
	return addElement(result, Long.parseLong(s));
    }

    public static long[] addElement(long[] arr, long n){
	long[] result = new long[arr.length+1];
	for(int i = 0; i < arr.length; i++)
	    result[i] = arr[i];
	result[arr.length] = n;
	return result;
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

}
