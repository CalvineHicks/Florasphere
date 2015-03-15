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
        int plant_list_size = 1;
        String plantlist[] = new String[plant_list_size]; //size of plant list (determines plant objects displayed)
        ListView listView = getListView();

        plantlist[0] = "testing"; //strings represent plant objects and are parsed by the FlorasphereListArrayAdaptor

        if (plantlist.length > 0){
            adapter = new FlorasphereListArrayAdaptor(this,plantlist);

           //when a list Item is clicked go to new activity using intent
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(FlorasphereListActivity.this, PlantInfoActivity.class);
                    //i.putExtra(); This would be used to add extra information outside of context to pass on to next class\
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
