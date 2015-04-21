package com.example.florasphere;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.*;
import android.widget.*;
import android.content.Intent;

import android.content.Context;

/**
 * Created by calvineh on 3/1/15.  Modified by Jessica Lynch and Noah Dillon.
 */
public class FlorasphereListActivity extends ListActivity{

    FlorasphereListArrayAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("tag", "oh my1");
        PlantStorage ps = new PlantStorage( this.getApplicationContext() ); //context to use to open or create the database name of the database file, or null for an in-memory database
        ps.initPlant();
        Log.i("tag", "FlorasphereListActifity.onCreate(): passed ps.initPlant().");
        final Plant[] plantlist = ps.getAllPlants(); //new Plant[plant_list_size]; //size of plant list (determines plant objects displayed)
        Log.i("tag", "FlorasphereListActifity.onCreate(): passed ps.getAllPlants().");
        int plant_list_size = plantlist.length;
        ListView listView = getListView();
        for (int i = 0; i< plant_list_size; i++) {
            plantlist[i] = new Plant(); //strings represent plant objects and are parsed by the FlorasphereListArrayAdaptor
            plantlist[i].setPlantName("test " + i);
            plantlist[i].setPlantPic(R.drawable.succulent_family);
        }
        //plantlist[0] = "testing"; //strings represent plant objects and are parsed by the FlorasphereListArrayAdaptor

        ImageButton settings = (ImageButton) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent k = new Intent(FlorasphereListActivity.this, SettingsActivity.class);
                startActivity(k);
                Log.i("tag", "Settings button pressed, SettingsActivity started");
            }
        });


        Button addPlant = (Button) findViewById(R.id.add_plant);
        addPlant.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent k = new Intent(FlorasphereListActivity.this, SearchSubmissionActivity.class);
                startActivity(k);
                Log.i("tag", "Add Plant button pressed, SearchSubmissionActivity started");
            }
        });
        Log.i("tag", "oh my5");
       if (plantlist.length > 0){
           Log.i("tag", "oh my6");
            adapter = new FlorasphereListArrayAdaptor(this,plantlist);

           //when a list Item is clicked go to new activity using intent
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("tag", ""+id);
                    Intent i = new Intent(FlorasphereListActivity.this, PlantInfoActivity.class);
                    i.putExtra("plant",plantlist[position]); //This would be used to add extra information outside of context to pass on to next class\
                    startActivity(i);
                }
            });
        Log.i("tag", "calling adapter row view"); //this is a comment that will be logged to LogCat

        listView.setAdapter(adapter);
        }
        else{
            //what do we do if the plant list is empty?
            //TO DO
        }
        //cursor.close();
        //dh.close();
    }
}
