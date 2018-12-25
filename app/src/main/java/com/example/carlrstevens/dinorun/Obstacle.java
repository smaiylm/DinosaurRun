package com.example.carlrstevens.dinorun;

import android.graphics.PointF;

public abstract class Obstacle extends Box{
    //The class is in similar idea to Bird and Dinosaur - this is an object with characteristics.
    private int mSpeed;


    public Obstacle(int x,int y, int size, int color,int speed){
        super(x,y,size,color);
        mSpeed = speed;
    }

    public Obstacle(int x, int y, int size){
        super(x,y,size,0xFF000000);
        mSpeed = 25;
    }

    public void setSpeed(int speed){ //At which speed do we want them to move at the direction of the player (dinosaur)?
        mSpeed = speed;
    }

    public int getSpeed(){
        return mSpeed;
    }

    public void move(){ //moving through coordinate change
        mOrigin.x-=mSpeed;
        mCorner.x-=mSpeed;
    }

    public int getType(){
        return -1;
    }



}