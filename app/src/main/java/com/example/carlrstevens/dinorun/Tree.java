package com.example.carlrstevens.dinorun;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

public class Tree extends Obstacle {

    public Tree(int x, int y, int speed) { //Constructor - attributes of a tree
        super(x,y,150, Color.parseColor("#008000"),speed);
    }

    public int getType(){ //we have decided for type tree to be defined/recognized as an int 1, and bird as 0.
        return 1; //1 = Tree, 0 = Bird
    }

}