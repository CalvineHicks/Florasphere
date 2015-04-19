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

import java.util.regex.Pattern;

public class SearchSubmissionActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchsubmission);
        final EditText plantSearch = (EditText)findViewById(R.id.search_text_by_name);
        final Context context = this;
        final PlantStorage ps = new PlantStorage(this);

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v) {
                Plant result;
                String strName =  plantSearch.getText().toString();
                Intent k = new Intent(SearchSubmissionActivity.this, SearchResultsActivity.class);

                if(validateInput(plantSearch.getText().toString())){
                    //Toast.makeText(context, "You set "+strName+" as the search parameter", Toast.LENGTH_LONG).show();
                    //result = ps.getPlant(plantSearch.getText().toString());
                    String keyIdentifer  = null;
                    k.putExtra("STRING_I_NEED", strName);
                    startActivity(k);
                }
                Toast.makeText(context, "please input a lowercase plant name with no symbols", Toast.LENGTH_LONG).show();
                Log.i("tag", "Search button pressed, SearchResultsActivity started");
            }
        });
    }

    protected boolean validateInput(String string)
    {
        if(string == ""){
            return false;
        }
        if(Pattern.matches("[A-Z a-z]+", string)){
            return true;
        }
        return false;


    }
}
