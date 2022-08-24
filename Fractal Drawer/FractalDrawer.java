//Created by Danial Syed
package com.company;
import java.util.Scanner;
import java.awt.Color;
//used for generating random floating point numbers in colorRandomizer
import java.util.Random;
public class FractalDrawer {
    private double totalArea=0;  // member variable for tracking the total area
    public FractalDrawer() {}  // contructor

    public double drawFractal(String type) {
        Canvas can = new Canvas(800,800);
        //if statements matches input to correct shape
        if (type.equals("circle")) {
            drawCircleFractal(50,200,200, Color.BLUE,can,0);
        } else if (type.equals("triangle")) {
            //change values if fractal doesn't look right, typically want higher y than x(at least that's what looks best on my end)
            drawTriangleFractal(200,300,250, 500,Color.BLUE,can,0);
        } else if (type.equals("rectangle")) {
            drawRectangleFractal(250, 350, 500, 300,Color.BLUE, can,0);
        }
        else{
            System.out.println("Incorrect Input!");
        }
        return totalArea;
    }

    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        Triangle fracTriangle = new Triangle(x, y, width, height);
        //random color so each iteration has a different color
        fracTriangle.setColor(colorRandomizer());
        //goes through iteration at least 7 times
        if (level < 8) {
            totalArea += fracTriangle.calculateArea();
            can.drawShape(fracTriangle);

            drawTriangleFractal(width / 2, height / 2, x - (width/2), y, c, can, level+1);
            drawTriangleFractal(width / 2, height / 2, x + width, y, c, can, level + 1);
            drawTriangleFractal(width / 2, height / 2, x + (width / 4), y - height, c, can, level + 1);
        }
    }

    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        Circle fracCircle = new Circle(x, y, radius);
        //random color so each iteration has a different color
        fracCircle.setColor(colorRandomizer());
        //goes through iteration at least 7 times
        if (level < 8) {
            totalArea += fracCircle.calculateArea();
            can.drawShape(fracCircle);

            drawCircleFractal(radius / 2, x + (2 * radius) , y, c, can, level + 1);
            drawCircleFractal(radius / 2, x - (2 * radius), y, c, can, level + 1);
            drawCircleFractal(radius / 2, x, y + (2 * radius) , c, can, level + 1);
            drawCircleFractal(radius / 2, x, y - (2 * radius), c, can, level + 1);
        }

    }

    public void drawRectangleFractal(double width, double height, double x, double
            y, Color c, Canvas can, int level) {
        Rectangle fracRectangle = new Rectangle(x, y, width, height);
        //random color so each iteration has a different color
        fracRectangle.setColor(colorRandomizer());
        //goes through iteration at least 7 times
        if (level < 8) {
            totalArea += fracRectangle.calculateArea();
            can.drawShape(fracRectangle);

            drawRectangleFractal(width / 2, height / 2, x - (width / 2), y - (height / 2), c, can, level + 1);
            drawRectangleFractal(width / 2, height / 2, x + width, y + height, c, can, level + 1);
            drawRectangleFractal(width / 2, height / 2, x - (width / 2), y + height, c, can, level + 1);
            drawRectangleFractal(width / 2, height / 2, x + width, y - (height / 2), c, can, level + 1);
        }
    }

    public Color colorRandomizer(){
        Random random = new Random();
        //randomly generates floating point numbers for r,g,b. The sum of these creates a new color
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        return new Color(r,g,b);

    }

    public static void main(String[] args) {
        System.out.println("Please enter: circle, triangle, or rectangle");
        Scanner myScanner = new Scanner(System.in);
        String input = myScanner.next();

        FractalDrawer fd = new FractalDrawer();
        //prints out prompt along with result of drawFractal(totalArea)
        System.out.println("The total area is:" + fd.drawFractal(input));
    }
}

