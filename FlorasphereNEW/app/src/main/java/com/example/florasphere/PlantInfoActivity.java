package com.example.florasphere;

import android.os.Bundle;
import android.app.*;
import android.util.Log;
import android.content.Context;
import android.view.*;
import android.widget.*;
import android.content.Intent;

/**
 * Created by calvinehicks on 3/12/15.
 */
public class PlantInfoActivity extends Activity {

    private final Context context = this;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Plant p = (Plant) i.getSerializableExtra("plant");
        setContentView(R.layout.plantinfo);
        TextView plantname = (TextView) findViewById(R.id.plant_name);
        Log.i("tag", p.getPlantName());
        plantname.setText(p.getPlantName());


        //get view objects
        TextView plantName = (TextView) findViewById(R.id.plant_name);
        ImageView plantImage = (ImageView) findViewById(R.id.plant_image);
        TextView waterFrequency = (TextView) findViewById(R.id.watering_frequency);
        TextView waterAmount = (TextView) findViewById(R.id.watering_amount);
        TextView lightAmount = (TextView) findViewById(R.id.light_amount);
        TextView genInfo = (TextView) findViewById(R.id.gen_info);


        //set view objects for plant
        plantName.setText(p.getPlantName().toString());
        plantImage.setImageDrawable(context.getResources().getDrawable(p.getPlantPic()));
        waterFrequency.setText("Watering Frequency: " + p.getWaterFreq() + " days");
        waterAmount.setText("Amount of Water Needed: " + p.getWaterAmt());
        lightAmount.setText("Amount of Light Needed: " + p.getLightAmt());
        genInfo.setText(p.getGenInfo());

    }

    public void onBackPressed(){
        gotoFlorasphereListActivity();
    }

    public void gotoFlorasphereListActivity(){
        Intent i = new Intent(this, FlorasphereListActivity.class);
        startActivity(i);
    }
}