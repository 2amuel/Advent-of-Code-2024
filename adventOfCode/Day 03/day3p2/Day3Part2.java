import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day3Part2{

    public static boolean doDont = true;

    public static void main(String[] args){
	Scanner input = null;

	File records = new File("./records.txt");

	try{
	    input = new Scanner(records);
	}
	catch(FileNotFoundException e){
	    System.out.println(e.getMessage());
	}
	String s;
	int total = 0;
	while(input.hasNext()){
	    s = input.nextLine();
	    total += findMul(s);

	}

	System.out.println(total);

    }

    public static int findMul(String s){

	int total = 0;

	while(true){
	    if(s.indexOf("mul(") == -1)
		break;
	    if(s.indexOf(",") == -1 || s.indexOf(")") == -1)
		break;
	    if(s.indexOf("do()") != -1 && s.indexOf("do()") < s.indexOf("mul(")){
		doDont = true;
		s = s.substring(s.indexOf("do()")+4);
	    }
	    else if(s.indexOf("don't()") != -1 && s.indexOf("don't") < s.indexOf("mul(")){
		doDont = false;
		s = s.substring(s.indexOf("don't()")+7);
	    }
	    else{
		s = s.substring(s.indexOf("mul("));
	        if(doDont && s.indexOf(",") <= 7 && s.indexOf(")") <= 11 && isNum(s.substring(4,s.indexOf(","))) && isNum(s.substring(s.indexOf(",")+1,s.indexOf(")"))))
		    total += Integer.parseInt(s.substring(4,s.indexOf(","))) * Integer.parseInt(s.substring(s.indexOf(",")+1,s.indexOf(")")));
	        s = s.substring(4);
	    }
	}

	return total;

    }

    public static boolean isNum(String s){

	for(int i = 0; i < s.length(); i++){
	    if(!(s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8' || s.charAt(i) == '9'))
		return false;
	}
	return true;
    }

}
