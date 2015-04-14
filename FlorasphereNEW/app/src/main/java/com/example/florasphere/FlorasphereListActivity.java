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
        UserPlantlist plantlist = UserPlantlist.getInstance(); //Determines plant objects displayed on user's main page.
        int plant_list_size     = plantlist.getSize();
        final Plant[] plantArray;

        PlantStorage ps = new PlantStorage( this.getApplicationContext() ); //context to use to open or create the database name of the database file, or null for an in-memory database
        ps.initPlant();
        Log.i("tag", "FlorasphereListActifity.onCreate(): passed ps.initPlant().");
        // ###### FAILING HERE ###### //
        Plant[] dbList = ps.getAllPlants();
        Log.i("tag", "FlorasphereListActifity.onCreate(): passed ps.getAllPlants().");

        //Temporary
        for ( Plant p : dbList )
        {
            plantlist.addPlant( p );
        }

        ListView listView = getListView();
        Log.i("tag", "FlorasphereListActifity.onCreate(): passed getListView().");


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
        if ( plant_list_size > 0){
            Log.i("tag", "oh my6");
            plantArray = plantlist.toArray( plant_list_size );
            adapter = new FlorasphereListArrayAdaptor(this, plantArray);

            //when a list Item is clicked go to new activity using intent
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("tag", ""+id);
                    Intent i = new Intent(FlorasphereListActivity.this, PlantInfoActivity.class);
                    i.putExtra( "plant", plantArray[(int)id] ); //This would be used to add extra information outside of context to pass on to next class\
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


/*
package com.example.florasphere;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.*;
import android.widget.*;
import android.content.Intent;


*/
/**
 * Created by calvineh on 3/1/15.
 *//*

public class FlorasphereListActivity extends ListActivity{

    FlorasphereListArrayAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final UserPlantlist userPlantlist = UserPlantlist.getInstance(); //size of plant list (determines plant objects displayed)
        int plant_list_size = userPlantlist.getSize();
        ListView listView = getListView();
//        for (int i = 0; i< plant_list_size; i++) {
//            plantlist[i] = new Plant(); //strings represent plant objects and are parsed by the FlorasphereListArrayAdaptor
//            plantlist[i].setPlantName("test"+i);
//        }
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

        if (plantlist.length > 0){
            adapter = new FlorasphereListArrayAdaptor(this,plantlist);

            //when a list Item is clicked go to new activity using intent
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("tag", ""+id);
                    Intent i = new Intent(FlorasphereListActivity.this, PlantInfoActivity.class);
//                    i.putExtra("plant",plantlist[(int)id]); //This would be used to add extra information outside of context to pass on to next class\
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
}*/
