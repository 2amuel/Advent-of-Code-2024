import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day7Part2{

    public static void main(String[] args){
	Scanner input = null;

	File equations = new File("./equations.txt");

	try{
	    input = new Scanner(equations);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}

	long total = 0;
	String s;

	while(input.hasNext()){
	    s = input.nextLine();
	    if(possibleEquation(s))
		total += Long.parseLong(s.substring(0, s.indexOf(":")));
	}

	System.out.println(total);

    }

    public static boolean possibleEquation(String s){
	long sum = Long.parseLong(s.substring(0, s.indexOf(":")));
	long[] nums = makeArray(s);
	return checkNumbers(sum, nums);
    }

    public static boolean checkNumbers(long target, long[] nums){
	if(nums.length == 0)
	    return false;
	if(nums[0] > target)
	    return false;
	if(nums.length == 1)
	    return nums[0] == target;
	return checkNumbers(target, mul(nums)) || checkNumbers(target, add(nums)) || checkNumbers(target, cat(nums));
    }

    public static long[] cat(long[] arr){
        long[] result = new long[arr.length-1];
        result[0] = Long.parseLong("" + arr[0] + arr[1]);
        for(int i = 2; i < arr.length; i++)
            result[i-1] = arr[i];
        return result;
    }

    public static long[] mul(long[] arr){
	long[] result = new long[arr.length-1];
	result[0] = arr[0] * arr[1];
	for(int i = 2; i < arr.length; i++)
	    result[i-1] = arr[i];
	return result;
    }

    public static long[] add(long[] arr){
        long[] result = new long[arr.length-1];
        result[0] = arr[0] + arr[1];
        for(int i = 2; i < arr.length; i++)
            result[i-1] = arr[i];  
        return result;
    }

    public static long[] makeArray(String s){
	s = s.substring(s.indexOf(":")+2);
	long[] arr = new long[0];
	while(true){
	    if(s.indexOf(" ") == -1){
		arr = addElement(arr, Long.parseLong(s));
		return arr;
	    }
	    arr = addElement(arr, Long.parseLong(s.substring(0, s.indexOf(" "))));
	    s = s.substring(s.indexOf(" ") + 1);
	}
    }

    public static long[] addElement(long[] arr, long n){
	long[] result = new long[arr.length+1];
	for(int i = 0; i < arr.length; i++)
	    result[i] = arr[i];
	result[arr.length] = n;
	return result;
    }

}
