package com.example.florasphere;

import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;

/**
 * Created by calvinehicks on 3/12/15.
 */
public class PlantInfoActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantinfo);

    }

    public void onBackPressed(){
        gotoFlorasphereListActivity();
    }

    public void gotoFlorasphereListActivity(){
        Intent i = new Intent(this, FlorasphereListActivity.class);
        startActivity(i);
    }
}