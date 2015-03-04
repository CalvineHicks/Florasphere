package com.example.florasphere;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.*;
import android.widget.*;
/**
 * Created by calvineh on 3/1/15.
 */
public class FlorasphereListActivity extends ListActivity{

    FlorasphereListArrayAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String plantlist[] = new String[0];
        ListView listView = getListView();


        if (plantlist.length > 0){
            adapter = new FlorasphereListArrayAdaptor(this,plantlist);

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
