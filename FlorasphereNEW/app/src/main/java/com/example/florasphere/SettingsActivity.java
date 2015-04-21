package com.example.florasphere;

import android.content.Context;
import android.os.Bundle;
import android.app.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.Intent;


/**
 * Created by madisonrockwell on 3/15/15.
 */
public class SettingsActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

        Button contactUs = (Button) findViewById(R.id.activity_contact_us);
        contactUs.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent k = new Intent(SettingsActivity.this, ContactUs.class);
                startActivity(k);
                Log.i("tag", "Contact Us button pressed, ContactUs started");
            }
        });
    }
}
