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

public class SearchResultsActivity extends ListActivity{

    SearchResultsArrayAdaptor adapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresults);

        final Plant plantArray[]= {
                new Plant( "African Violet", R.drawable.african_violet , 6, Plant.WaterAmt.LIGHT, Plant.LightAmt.FULL,
                        "Allow the top 1-2 inches of soil in an African Violet plant to dry out "   +
                        "before watering. Avoid using water on an African Violet plant that has "   +
                        "passed through a softener or water containing chemicals. Always water "    +
                        "close to the soil to prevent water from getting on the leaves and remove " +
                        "any excess water in the drainage plate after 15 min. Prefer full bright  " +
                        "light, i.e. close to an East facing window. Prefer temperatures between "  +
                        "75-80 degrees during the day and about 10 degrees cooler at night. Keep "  +
                        "away from cold drafts and heating vents. Keep root bound in a small pot "  +
                        "to encourage greater flower production." ),
                new Plant( "Boston Fern", R.drawable.boston_fern, 5, Plant.WaterAmt.MEDIUM, Plant.LightAmt.FULL,
                        "Thrive in humid conditions with bright indirect sunlight, i.e. close to "  +
                        "east or west facing window. Prefer daytime temp. from 65-75 degrees and "  +
                        "evening temp. from 55-65 degrees.  Cool evening temps will prevent "       +
                        "fungus growth. If climate is dry, mist Boston Fern once a day with a "     +
                        "spray bottle filled with clean tepid water. In summer months, water more " +
                        "frequently and try not to let soil dry out between waterings. In winter "  +
                        "months, allow soil to dry out a little before watering, but resume "       +
                        "regular watering schedule when fronds start to appear. Its pot must be "   +
                        "clean and have drainage holes in the bottom. " )
                };

        ListView listView = getListView();

        adapter = new SearchResultsArrayAdaptor(this, plantArray);

        //when a list Item is clicked go to new activity using intent
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("tag", "" + id);
                Intent i = new Intent(SearchResultsActivity.this, PlantInfoActivity.class);
                i.putExtra("plant", plantArray[(int) id]); //This would be used to add extra information outside of context to pass on to next class\
                startActivity(i);
            }
        });

        listView.setAdapter(adapter);

    }
}
