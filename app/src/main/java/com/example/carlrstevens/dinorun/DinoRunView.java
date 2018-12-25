package com.example.carlrstevens.dinorun;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

public class DinoRunView extends View {

    public static final String TAG = "DinoRunView";
    private Paint mBackgroundPaint;
    private Paint mHorizonPaint;
    private Paint mText;
    private int mHorizon;
    private boolean mDimensions = false;

    private Dinosaur dino; //the main character of the game - the player
    private ArrayList<Obstacle> obs = new ArrayList<Obstacle>(); //We store obstacles in an ArrayList

    int infinity = 100;
    float velocity = 0;
    boolean gameOver = false; //the game is running at the beginning
    Intent intent = new Intent(this.getContext(), EndActivity.class);   //This intent is ran when the player loses - takes the player to the EndActvity
    int score = 0; //initial score of zero
    int difficulty = 90;
    int speed = 20;

    private Drawable dinoImage;
    private Drawable treeImage;
    private Drawable birdImage;

    private Obstacle cStore;
    private int lives;

    public DinoRunView(Context context){
        this(context,null);
    }

    public DinoRunView(Context context, AttributeSet attrs) { //Constructor - the design of the main game page, using Paint.
        super(context, attrs);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
        mHorizonPaint = new Paint();
        mHorizonPaint.setColor(0xffcdc9c9);
        mHorizonPaint.setTextSize(50);
        dino = new Dinosaur();
        dinoImage = context.getResources().getDrawable(R.drawable.download);
        treeImage = context.getResources().getDrawable(R.drawable.tree);
        birdImage = context.getResources().getDrawable(R.drawable.bird);

        lives = 1; //The user has x# lives at the beginning

        //mText.setColor(Color.BLACK);
        //mText.setTextSize(30);
    }


    @Override
    protected void onDraw(Canvas canvas){
        //Log.d(TAG,"Draw Ran");

        if(!mDimensions)
        {
            mHorizon = this.getHeight()/3*2;
            //dino.setCoordinates(this.getWidth()/8, mHorizon);
            //dino = new Dinosaur(this.getWidth()/8, mHorizon, 100, 0xFF000000);
            dino.setOrigin(new PointF(getWidth()/8,mHorizon));
            dino.setCorner(new PointF(getWidth()/8+200,mHorizon+200));
            mDimensions=true;
        }

        //Log.d(TAG,"Draw Ran");
        if (lives <= 0) //if the user does not have any lives left, do ...
        {
            Log.d(TAG,"GAME OVER");
            Toast.makeText(getContext(), "Your score: " + score, Toast.LENGTH_LONG).show();  //show the user's score
            intent.putExtra("1", score);

            gameOver = false;
            score = 0;
            this.getContext().startActivity(intent); //Take the user to EndActivity through intents
        }
        else
        {
            //Log.d(TAG,"Lives: " + lives);

            canvas.drawPaint(mBackgroundPaint);
            canvas.drawRect(0,mHorizon,this.getWidth(), this.getHeight(), mHorizonPaint);
            canvas.drawText(Integer.toString(score),100,100,mHorizonPaint );
            update();
            check();

            RectF rF = dino.getRect();
            dinoImage.setBounds((int)rF.left, (int)rF.top, (int)rF.right, (int)rF.bottom);
            dinoImage.draw(canvas);




            for(Obstacle ob : obs) //for a whole ArrayList of obstacles, do...
            {
                //canvas.drawRect(leftT, topT, rightT, bottomT, T.getColor());
                RectF obRF = ob.getRect();
                if(ob.getType() == 1){ //IF TREE
                    treeImage.setBounds((int)obRF.left, (int)obRF.top, (int)obRF.right, (int)obRF.bottom);
                    treeImage.draw(canvas);
                }
                else{ //otherwise, it's a bird
                    birdImage.setBounds((int)obRF.left, (int)obRF.top, (int)obRF.right, (int)obRF.bottom);
                    birdImage.draw(canvas);
                }

            }
            invalidate();
        }
    }

    public void update(){
        dino.jUpdate(mHorizon,getHeight());
        for(Obstacle obstacle : obs){
            obstacle.move(); //move obstacles
        }
        if(score%20 == 0)
        {
            difficulty = difficulty-1;
        }

        if(dino.mOrigin.y == 200)
        {
            velocity = 0;
        }

        if(infinity % 10 == 0)
        {
            dinoImage = this.getResources().getDrawable(R.drawable.download);
        }
        else
        {
            dinoImage = this.getResources().getDrawable(R.drawable.run);
        }

        if(infinity%30 == 0 && (int)Math.floor(Math.random() * 100) >= difficulty)
        {
            if((int)Math.floor(Math.random() * 2) ==1 )
            {
                obs.add(new Bird(getWidth(),mHorizon-100, speed )); // adds bird above horizon
                score++;
                // add bird
            }
            else
            {
                obs.add(new Tree(getWidth(),mHorizon, speed));
                score++;
                // add tree
            }
        }
        infinity++;
    }

    public void check(){
        for(Obstacle obstacle : obs){ //Check for collision
            if((dino.getY()>= obstacle.getY() && dino.getY() <= obstacle.getCY()) && (dino.getCX()>= obstacle.getX() && dino.getCX() <= obstacle.getCX())){
                if(cStore != obstacle){
                    cStore = obstacle;
                    lives--;   //if there is a collision, subtract lives
                    Log.d(TAG, "Collision occured");
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event){

        float x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (x < getWidth() / 2) { // if tap on left side of screen
                    //Log.d(TAG,"Jump Start");
                    if(!dino.getJump() && !dino.getDuck()){ // if the dino is not jumping or ducking already
                        dino.jStart(); // start the jump animation
                    }
                }
                if (x>getWidth()/2)
                {
                    if(!dino.getJump() && !dino.getDuck()) {
                        dino.duck(); // start the duck animation
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if(x>getWidth()/2)
                {
                    if (!dino.getJump() && dino.getDuck()) { // if the dino isnt midair, (meaning it is currently ducking), unduck
                        dino.unDuck();
                    }
                }

        }
        return true;
    }
}