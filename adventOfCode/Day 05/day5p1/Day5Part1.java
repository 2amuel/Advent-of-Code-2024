import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day5Part1{

    public static void main(String[] args){
	Scanner input = null;

	File list = new File("./rules.txt");

	try{
	    input = new Scanner(list);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}
	String[] rules = new String[0];
	String s = "|";
	while(input.hasNext() && s.contains("|")){
	    s = input.nextLine();
	    if(s.contains("|")){
		rules = addEntry(rules, s.substring(0,2));
		rules = addEntry(rules, s.substring(3));
	    }	    
	}


	int count = 0;

	while(input.hasNext()){
	    s = input.nextLine();
	    if(checkRules(s, rules)){
		count += Integer.parseInt(s.substring(s.length()/2-1, s.length()/2+1));
	    }	
	}
	System.out.println(count);
    }

    public static boolean checkRules(String s, String[] rules){
	String t;
	for(int i = 0; i < rules.length; i += 2){
	    t = s;
	    while(true){
		if(!(t.contains(rules[i]) && t.contains(rules[i+1])))
		    break;
		if(t.indexOf(rules[i]) > t.indexOf(rules[i+1])){
		    return false;
		}
		t = t.substring(t.indexOf(rules[i])+3);
	    }

	}

	return true;

    }

    public static String[] addEntry(String[] arr, String s){
	String[] result = new String[arr.length+1];
	for(int i = 0; i < arr.length; i++)
	    result[i] = arr[i];
	result[arr.length] = s;
	return result;
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

}
