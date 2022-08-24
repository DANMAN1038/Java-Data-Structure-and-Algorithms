//Created by Danial Syed
package com.company;

import java.awt.*;

public class Rectangle{
    //Initialization
    double xPos = 0.0;
    double yPos = 0.0;
    static double width = 0.0;
    static double height = 0.0;
    Color color;


    public Rectangle(double x, double y, double w, double h){
        //set inputs to values used in FractalDrawer
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }

    public static double calculatePerimeter(){
        double perimeter = (width*2)+(height*2);
        return perimeter;
    }
    public static double calculateArea(){
        double area = width*height;
        return area;
    }

    public void setPos(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setWidth(double width){
        this.width = width;
    }
    public void setColor(Color color1) {
        color = color1;
    }
    public Color getColor(){
        return color;
    }
    public double getXPos(){ return xPos; }
    public double getYPos(){
        return yPos;
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }

}
