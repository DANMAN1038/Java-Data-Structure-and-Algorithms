package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        String num = "471";
        //iterativeReverse(471);
        String itReversed = "";
        int last = 0;
        String recReversed = "";
        //int rec = recursiveReverse(571);
        //System.out.println("Reversed int: " + rec);
        Scanner myScanner = new Scanner(System.in);
        String input = myScanner.nextLine();
        Scanner intScanner = new Scanner(input);
        int result = fibonacciRecursive(Integer.parseInt(input));
        System.out.println("The fibonacci number is: " + result);
    }
    public static int fibonacciRecursive(int n){
        int result = 0;
        if(n<=1){
            return n;
        }
        else {
            result = fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
            return result;
        }
    }

    public static int recursiveReverse(int num) {
        String numString = String.valueOf(num);
        String x = helper(numString, "");
        System.out.println(x);
        int recReversed=Integer.parseInt(x);
        return recReversed;
    }
    public static String helper(String num, String reversed){
        if(num.equals("")){
            return reversed;
        }
        else {
            //reversed stores last number through each iteration
            //num stores everything that is left
            //471 -
            //47 1
            //4 7
            //- 4
            String r = reversed + num.charAt(num.length()-1);
            return helper(num.substring(0,num.length()-1),r );

        }
    }

    public static int iterativeReverse(int num) {
        String numString = String.valueOf(num);
        String itReversed = "";
        for (int i = numString.length() - 1; i >= 0; i--) {
            itReversed = itReversed + numString.charAt(i);
        }
        System.out.println("Reversed int: " + itReversed);
        int last=Integer.parseInt(itReversed);
        return last;
    }
}
