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

public class SearchResultsActivity extends ListActivity{

    SearchResultsArrayAdaptor adapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresults);
        Context context = this;

        ListView listView = getListView();

        //variables
        final PlantStorage ps = new PlantStorage(this);
        ps.initPlant();
//        final Plant resultPlant[] = new Plant[1];
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
        final Plant resultPlant[] = {ps.getPlant(plantSearchText)};
        if(resultPlant[0]==null){
            Log.i("tag", "oh nooos! " + plantSearchText);
        }
        else{
            Log.i("tag", "meybe it is working: " + resultPlant[0].getWaterFreq() + "\n resorce should be: "+ R.drawable.succulent_family);
        }
        adapter = new SearchResultsArrayAdaptor(this, resultPlant);

        //when a list Item is clicked go to new activity using intent
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("tag", "" + id);
                Intent i = new Intent(SearchResultsActivity.this, PlantInfoActivity.class);
                i.putExtra("plant", resultPlant[(int) id]); //This would be used to add extra information outside of context to pass on to next class\
                startActivity(i);
            }
        });

        listView.setAdapter(adapter);

        //TextView textView = (TextView) rowView.findViewById(R.id.plant_name);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.plant_image);
    }
}
