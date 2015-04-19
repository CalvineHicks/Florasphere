package com.example.florasphere;

/**
 * Created by madisonrockwell on 3/15/15.
 */

import android.content.Context;
import android.os.Bundle;
import android.app.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.Intent;



public class SearchResultsActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_2);
        Context context = this;


        //variables
        final PlantStorage ps = new PlantStorage(this);
        Plant resultPlant;
        //retrieve string from serachSubmissionActivity
        String plantSearchText;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                plantSearchText= null;
            } else {
                plantSearchText= extras.getString("STRING_I_NEED");
            }
        } else {
            plantSearchText= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        Toast.makeText(context, "You set "+plantSearchText+" as the search parameter", Toast.LENGTH_LONG).show();
        resultPlant = ps.getPlant(plantSearchText);

        //TextView textView = (TextView) rowView.findViewById(R.id.plant_name);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.plant_image);
    }
}
