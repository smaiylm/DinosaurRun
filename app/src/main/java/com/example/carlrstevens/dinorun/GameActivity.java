package com.example.carlrstevens.dinorun;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends SingleFragmentActivity {
    //This is the starting place of the project - the main activity.
    @Override
    protected Fragment createFragment(){
        return com.example.carlrstevens.dinorun.DinoRunFragment.newInstance();
    }
}
