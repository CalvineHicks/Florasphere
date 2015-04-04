package com.example.florasphere;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.*;
import android.widget.*;
import android.content.Intent;
/**
 * Created by calvineh on 3/1/15.
 */
public class FlorasphereListActivity extends ListActivity{

    FlorasphereListArrayAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int plant_list_size = 10;
        final Plant plantlist[] = new Plant[plant_list_size]; //size of plant list (determines plant objects displayed)
        ListView listView = getListView();
        for (int i = 0; i< plant_list_size; i++) {
            plantlist[i] = new Plant(); //strings represent plant objects and are parsed by the FlorasphereListArrayAdaptor
            plantlist[i].setPlantName("test"+i);
        }
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
                    i.putExtra("plant",plantlist[(int)id]); //This would be used to add extra information outside of context to pass on to next class\
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
