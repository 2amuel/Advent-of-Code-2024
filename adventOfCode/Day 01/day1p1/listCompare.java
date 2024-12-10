import java.util.Scanner;

public class listCompare{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int difference = 0;
        while(input.hasNext()){
            String s = input.nextLine();
            int[] arr1 = sort(arrayify(s.substring(0,5)));
            int[] arr2 = sort(arrayify(s.substring(8)));
            difference += dif(arr1,arr2);
        }
        System.out.println(difference);
    }
    
    public static int dif(int[] arr1, int[] arr2){
        int result = 0;
        for(int i = 0; i < arr1.length; i++)
            result += Math.abs(arr1[i]-arr2[i]);
        return result;
    }
    
    public static int[] sort(int[] arr){
        
        if(arr.length <= 1)
            return arr;
        
        int middle = arr.length/2;
        int[] left = new int[middle];
        int[] right = new int[arr.length-middle];
        
        for(int i = 0; i < left.length; i++)
            left[i] = arr[i];
        for(int i = 0; i < right.length; i++)
            right[i] = arr[i+middle];
            
        int[] leftSorted = sort(left);
        int[] rightSorted = sort(right);
        int l = 0;
        int r = 0;
        int[] result = new int[arr.length];
        while(true){
            if(l >= leftSorted.length && r >= rightSorted.length)
                break;
            if(l >= leftSorted.length){
                result[l+r] = rightSorted[r];
                r++;
            }
            else if(r >= rightSorted.length){
                result[l+r] = leftSorted[l];
                l++;
            }
            else if(leftSorted[l] <= rightSorted[r]){
                result[l+r] = leftSorted[l];
                l++;
            }
            else{
                result[l+r] = rightSorted[r];
                r++;
            }
        }
        
        return result;
        
    }
    
    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i]);
            System.out.println();
    }
    
    public static int[] arrayify(String s){
        int[] arr = new int[s.length()];
        for(int i = 0; i< arr.length; i++)
            arr[i] = Integer.parseInt(s.substring(i, i+1));
        return arr;
    }
    
}



