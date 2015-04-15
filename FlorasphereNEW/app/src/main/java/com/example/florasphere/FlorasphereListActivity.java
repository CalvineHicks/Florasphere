package com.example.florasphere;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.Intent;

/**
 * Created by calvineh on 3/1/15.  Modified by Jessica Lynch and Noah Dillon countless times.
 */
public class FlorasphereListActivity extends ListActivity
{

    FlorasphereListArrayAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("tag", "FlorasphereListActivity.onCreate(): passed supper.onCreate(savedInstanceState) and setContentView(R.layout.activity_main");

        PlantStorage ps = new PlantStorage( this.getApplicationContext() ); //context to use to open or create the database name of the database file, or null for an in-memory database
        ps.initPlant();
        Log.i("tag", "FlorasphereListActivity.onCreate(): passed ps.initPlant().");
        final Plant[] dbList = ps.getAllPlants(); // **** dbList is USED FOR DEMO ONLY -- remove after search implemented ****
        Log.i("tag", "FlorasphereListActivity.onCreate(): passed ps.getAllPlants().");

        ListView listView = getListView();
        Log.i("tag", "FlorasphereListActivity.onCreate(): passed getListView().");
        ImageButton settings = (ImageButton) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent k = new Intent(FlorasphereListActivity.this, SettingsActivity.class);
                startActivity(k);
                Log.i("tag", "Settings button pressed and SettingsActivity started");
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
        Log.i( "tag", "dbList.length = " + dbList.length );
        if ( dbList.length > 0 )
        {
            adapter = new FlorasphereListArrayAdaptor(this, dbList );

            //when a list Item is clicked go to new activity using intent
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("tag", ""+id);
                    Intent i = new Intent(FlorasphereListActivity.this, PlantInfoActivity.class);
                    i.putExtra( "plant", dbList[(int)id] ); //This would be used to add extra information outside of context to pass on to next class\
                    startActivity(i);
                }
            });
            Log.i("tag", "calling adapter row view"); //this is a comment that will be logged to LogCat

            listView.setAdapter(adapter);
        }
    }
}