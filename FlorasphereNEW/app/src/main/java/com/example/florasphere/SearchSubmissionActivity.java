package com.example.florasphere;

/**
 * Created by madisonrockwell on 3/15/15.
 */
import android.os.Bundle;
import android.app.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.Intent;

public class SearchSubmissionActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchsubmission);

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v) {
                Intent k = new Intent(SearchSubmissionActivity.this, SearchResultsActivity.class);
                startActivity(k);
                Log.i("tag", "Search button pressed, SearchResultsActivity started");
            }
        });
    }
}
