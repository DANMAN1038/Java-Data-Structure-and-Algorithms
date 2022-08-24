package com.company;
public class HeatGrid {
    int[][] grid;
    int totalHeat = 0;

    public static void main(String[] args) {
        HeatGrid grid = new HeatGrid(9,8);
        grid.placeSource("c",2,3);
        grid.placeSource("i",2,6);
        grid.placeSource("r",8,7);
        System.out.println(grid);
    }
    public HeatGrid(int height, int width) {
        grid = new int[height][width];
    }
    private int decayHeat(double decay, int heat, int distance) {
        return (int) (heat * Math.exp(-1 * decay * distance));
    }
    public boolean placeSource(String src, int x, int y){
        int heat = 0;
        double decay = 0.00;
        int distance = 0;
        if (x < 0 || y < 0 || x > grid.length || y > grid[0].length){
            return false;
        }
        if(src.equals("l")){
            heat = 1;
            distance = 0;
            decay = 0.00;

        }
        else if(src.equals("c")){
            heat = 4;
            distance = 2;
            decay = 0.50;

        }
        else if(src.equals("f")){
            heat = 10;
            distance = 4;
            decay = 0.35;

        }
        else if(src.equals("i")){
            heat = -2;
            distance = 1;
            decay = 0.50;

        }
        else if(src.equals("r")){
            heat = -8;
            distance = 3;
            decay = 0.20;

        }
        else if(src.equals("g")){
            heat = -20;
            distance = 5;
            decay = 0.15;

        }
        int diff = 0;
        for(int i = x-distance; i <= x+distance; i++) {
            for (int j = y - distance; j <= y + distance; j++) {
                if (i >= 0 && j >= 0 && i < grid.length && j < grid[i].length) {
                    diff = Math.max(Math.abs(i-x), Math.abs(j-y));
                    grid[i][j] += decayHeat(decay, heat, diff);
                }
            }
        }
        return true;
    }
    public String toString() {
        String newString = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                newString += grid[i][j] + " ";
            }
            newString += "\n";
        }
        return newString;
    }
    public int[][] getHeats(){
        return grid;
    }
    public int getHeat(int x, int y){
        if(x < 0 || y < 0 || x > grid.length || y > grid[0].length){
            return grid[x][y];
        }
        else{
            return Integer.MAX_VALUE;
        }
    }
    public int getNetHeat(){
        for(int i=0; i <= grid.length; i++){
            for(int j=0; j<= grid[i].length; j++){
                totalHeat += grid[i][j];
            }
        }
        return totalHeat;
    }
}
