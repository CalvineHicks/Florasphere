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
        String plantSearchState;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                plantSearchText= null;
                plantSearchState= null;
            } else {
                plantSearchText = extras.getString("STRING_TO_EXTRACT");
                plantSearchState = extras.getString("STATE");
            }
        } else {
            plantSearchText= (String) savedInstanceState.getSerializable("STRING_TO_EXTRACT");
            plantSearchState = (String) savedInstanceState.getSerializable("STATE");

        }

        Toast.makeText(context, "You set "+plantSearchText+" as the search parameter", Toast.LENGTH_LONG).show();
        Toast.makeText(context, "You set "+plantSearchState+" as the search state", Toast.LENGTH_LONG).show();
        resultPlant = ps.getPlant(plantSearchText);

        //TextView textView = (TextView) rowView.findViewById(R.id.plant_name);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.plant_image);
    }
}
