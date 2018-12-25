package com.example.carlrstevens.dinorun;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EndActivity extends AppCompatActivity {
    private Button mRestartButton;
    private Button mQuitButton;
    private int score;  //score is based on how many obstacles the player dodged
    //    private Button mViewScoreButton;
    private static final String TAG = "EndActivity";
    private String text;
    String Score = "You died! Your Score is : ";
    //Intent mIntent = getIntent();
    //int ScoreNum = mIntent.getIntExtra("1", 0);
    DinoRunView DinoScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end);


        //text = Integer.toString(DinoScore.getScore());

//        mViewScoreButton = (Button) findViewById(R.id.score);
//        mViewScoreButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(EndActivity.this,
//                        "hw;oqe",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });



        mRestartButton = (Button) findViewById(R.id.restart); //button that restarts the game - takes the user back into GameActivity.
        mRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //if clicked, run the method restart(), which has an intent

                //       game.start   //restart the game
                restart();
            }

        });


    }

    public void restart() { //restarting the game takes you into GameActivity - we used intents to transfer the user from one activity to another, in this case.
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}