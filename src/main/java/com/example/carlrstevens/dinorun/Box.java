package com.example.carlrstevens.dinorun;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public abstract class Box {
    //initializing new variables
    public PointF mOrigin; //Access to PointF
    public PointF mCorner;
    public Paint mColor; //Access to paint
    public int mSize;

    public Box(int x, int y, int size, int color) { //constructor
        mOrigin =  new PointF(x,y);
        mSize = size;
        mCorner = new PointF(x+size,y+size);
        mColor = new Paint();
        mColor.setColor(color);
    }

//all possible functions of a box such as changing coordinates or color.


    public void setCorner(PointF corner) {
        mCorner = corner;
    }

    public PointF getCorner(){
        return mCorner;
    }

    public PointF getOrigin() {
        return mOrigin;
    }

    public void setOrigin(PointF origin){
        mOrigin = origin;
    }

    public float getX(){
        return mOrigin.x;
    }

    public float getCX(){
        return mCorner.x;
    }

    public float getY(){
        return mOrigin.y;
    }

    public float getCY(){
        return mCorner.y;
    }

    public Paint getColor() {
        return mColor;
    }

    public void setColor(Paint mColor) {
        this.mColor = mColor;
    }

    public void addY(int y){
        mOrigin.y += y;
        mCorner.y += y;
    }

    public RectF getRect(){
        return new RectF(mOrigin.x-mSize/2, mOrigin.y-mSize/2, mOrigin.x+mSize/2, mOrigin.y+mSize/2);
    }


    public float getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
        mCorner = new PointF(getX()+size,getY()+size);
    }

    public String toString(){
        return mOrigin  + " " + mCorner;
    }

}