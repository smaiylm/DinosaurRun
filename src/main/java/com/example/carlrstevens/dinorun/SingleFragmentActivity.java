package com.example.carlrstevens.dinorun;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public abstract class SingleFragmentActivity extends FragmentActivity {

    private Button mButton;
    protected abstract Fragment createFragment();
    private String m_Text;
    private int m_Num;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        mButton = (Button)findViewById(R.id.game_start);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call fragment
                FragmentManager manager = getSupportFragmentManager();
                Fragment fragment = manager.findFragmentById(R.id.fragment_container);
                if (fragment == null) {
                    fragment = createFragment();
                    manager.beginTransaction().add(R.id.fragment_container, fragment).commit();
                }

                //Remove Button
                ViewGroup layout = (ViewGroup) mButton.getParent();
                if(null!=layout){
                    layout.removeView(mButton);
                }
            }
        });

    }
}