package com.example.carlrstevens.dinorun;

public class Dinosaur extends Box {

    private String TAG = "Dinosaur";
    private int MAX_VEL = -30;
    private int GRAVITY = 2;
    private int dCount;
    private boolean dBool;
    private boolean jBool;
    private boolean imgUpdate;
    private int jVel; //Velocity
//This class contains all the possible functions of a dinosaur (player).Duck, jump, unduck are examples.

    public Dinosaur() {  //Constructor - attributes of a dinosaur.
        super(0, 0, 150, 0xFF000000/*RED*/);
        jBool = false; //Boolean for is jump activated
        dBool = false;
        dCount = 0;
        jVel = MAX_VEL;
        imgUpdate = true;
    }

    public void jUpdate(int horizon, int height) {

        if (jBool) { //If jumping is activated
            if (getY() <= horizon) {
                //Log.d(TAG,"jVelocity: " + jVel);
                addY(jVel);
                jVel+=GRAVITY;
            } else {
                jBool = false;
                jVel = MAX_VEL;
                mOrigin.y = horizon;
                dBool = false;

            }
        }
    }

    public void jStart() {
        //Log.d(TAG,"Jumping Started");
        jBool = true;
        addY(jVel);
        jVel++;
    }

    public void duck() {
        mOrigin.offset(0, 50);
        dBool = true;
    }

    public void unDuck() { //opposite of duck() method
        mOrigin.offset(0, -50);
        dBool = false;
    }

    public boolean getJump() { //return the corresponding booleans
        return jBool;
    }

    public boolean getDuck() { //return the corresponding booleans
        return dBool;
    }
}