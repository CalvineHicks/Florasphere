package com.example.florasphere;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Noah on 4/17/2015.
 */
public class SearchResultsArrayAdaptor extends ArrayAdapter<Plant>
{
    private final Context context;
    private final Plant[] values;
    private UserPlantlist plants;
    //private DatabaseHelper dh;

    public SearchResultsArrayAdaptor(Context context, Plant[] values)
    {
        super(context, R.layout.list_plant_list, values);
        this.context = context;
        this.values = values;
        plants = UserPlantlist.getInstance(context);
        //dh = new DatabaseHelper(context);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Plant currentPlant = values[position];
        View rowView = inflater.inflate(R.layout.list_plant_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.plant_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.plant_image);
        ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.plant_status);
        textView.setText(currentPlant.getPlantName());

        imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.add));

        imageView.setImageDrawable(context.getResources().getDrawable(currentPlant.getPlantPic()));
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                plants.addPlant(currentPlant);
            }
        });
        Log.i("tag", currentPlant.getPlantName()); //sample message to logcat (for debugging)
        return rowView;
    }
}
