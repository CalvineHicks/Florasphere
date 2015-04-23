package com.example.florasphere;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import android.view.*;
import android.util.Log;


/**
 * Created by calvineh on 3/1/15.
 */

public class FlorasphereListArrayAdaptor extends ArrayAdapter<Plant>
{
    private final Context context;
    private final Plant[] values;
    private String username;
    private int wateringStatus;
    String waterOneThird = "";


    //private DatabaseHelper dh;

    public FlorasphereListArrayAdaptor(Context context, Plant[] values	)
    {
        super(context, R.layout.list_plant_list, values);
        this.context = context;
        this.values = values;
        //dh = new DatabaseHelper(context);
    }

    public View getView(final int position, final View convertView, final ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Plant currentPlant = values[position];
        View rowView = inflater.inflate(R.layout.list_plant_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.plant_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.plant_image);
        final ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.plant_status);
        textView.setText(currentPlant.getPlantName());
        //if(currentPlant.getWaterAmt() == Plant.WaterAmt.LIGHT) {
        if( currentPlant.getLastWatering() < 1 ) {
            imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.water_droplet_empty));
        }
        else if( currentPlant.getLastWatering() < currentPlant.getWaterFreq()/2 ) {
            imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.water_droplet_half));
        }
        else {
            imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.water_droplet_full));
        }

        imageView.setImageDrawable(context.getResources().getDrawable(currentPlant.getPlantPic()));
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentPlant.waterPlant();
                imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.water_droplet_full));
                imageButton.refreshDrawableState();
            }
        });
        Log.i("tag",currentPlant.getPlantName()); //sample message to logcat (for debugging)
        return rowView;
    }
}


