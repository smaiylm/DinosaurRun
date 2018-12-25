package com.example.carlrstevens.dinorun;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;



public class Bird extends Obstacle {

    public Bird(int x, int y, int speed){ //attributes of an object bird - coordinates, dimension, color.
        super(x,y,100, Color.parseColor("#FFCB6C"),speed);
    }

    public int getType(){
        return 0;//1 = Tree, 0 = Bird
    }
}