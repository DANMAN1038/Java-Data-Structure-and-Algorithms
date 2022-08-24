//Created by Danial Syed
package com.company;
import java.awt.*;
public class Circle {
//Initialization
    double xPos = 0.0;
    double yPos = 0.0;
    double radius = 0.0;
    Color color;
    public Circle(double x, double y, double r){
        //set inputs to values used in FractalDrawer
        xPos = x;
        yPos = y;
        radius = r;
        //Circle xPos = new Circle;
    }
    public double calculatePerimeter(){
        double perimeter = 2*radius*Math.PI;
        return perimeter;
    }
    public double calculateArea(){
        double area = Math.PI*(radius*radius);
        return area;
    }
    public void setColor(Color color1){
        color = color1;
    }
    public void setPos(double xPos, double yPos){
        xPos = xPos;
        yPos = yPos;
    }
    public void setRadius(double radius){
        radius = radius;
    }
    public Color getColor(){
        return color;
    }
    public double getXPos(){
        return xPos;
    }
    public double getYPos(){
        return yPos;
    }
    public double getRadius(){
        return radius;
    }
}
