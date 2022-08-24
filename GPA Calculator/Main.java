package com.company;
import java.util.Scanner;
    public class GPA {
        public static void main(String[] args) {

            System.out.println("Type a sentence, then press enter");
            Scanner myScanner = new Scanner(System.in);

            String input = myScanner.nextLine();
            Scanner stringScanner = new Scanner(input);
            int count = 0;
            int totalCredits = 0;
            double totalgrade = 0;
            int previousCredits = 0;
            while (stringScanner.hasNext()) {
                String inputText = stringScanner.next();
                int credits = stringScanner.nextInt();
                System.out.println("Input " + count + ":" + inputText);
                //top
                totalgrade += helper(inputText) * credits;
                //bottom
                totalCredits = totalCredits + credits;

            }
            double gpa = ((totalCredits + totalgrade) / totalCredits) - 1.0;
            System.out.println("The GPA is:" + gpa);
        }

        public static double helper(String grade) {
            if (grade.equals("a")) {
                return 4.0;
            } else if (grade.equals("a-")) {
                return 3.667;
            } else if (grade.equals("b+")) {
                return 3.333;
            } else if (grade.equals("b")) {
                return 3.0;
            } else if (grade.equals("b-")) {
                return 2.667;
            } else if (grade.equals("c+")) {
                return 2.333;
            } else if (grade.equals("c")) {
                return 2.0;
            } else if (grade.equals("c-")) {
                return 1.667;
            } else if (grade.equals("d+")) {
                return 1.333;
            } else if (grade.equals("d")) {
                return 1.0;
            } else if (grade.equals("f")) {
                return 0.0;
            } else {
                return -1;
            }
        }
    }


