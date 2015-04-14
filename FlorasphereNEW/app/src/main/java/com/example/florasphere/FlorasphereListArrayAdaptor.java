package com.example.florasphere;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
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

    public View getView(int position, View convertView, ViewGroup parent)
    {
          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          Plant currentPlant = values[position];
          View rowView = inflater.inflate(R.layout.list_plant_list, parent, false);
          TextView textView = (TextView) rowView.findViewById(R.id.plant_name);
          ImageView imageView = (ImageView) rowView.findViewById(R.id.plant_image);
          ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.plant_status);
        textView.setText(currentPlant.getPlantName());
        if(currentPlant.getWaterAmt() == Plant.WaterAmt.LIGHT) {
            imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.water_drop_1outof3));
        }
        else if(currentPlant.getWaterAmt() == Plant.WaterAmt.MEDIUM) {
            imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.water_drop_2outof3));
        }
        else if(currentPlant.getWaterAmt() == Plant.WaterAmt.SOAK) {
            imageButton.setImageDrawable(context.getResources().getDrawable(R.drawable.water_drop_3outof3));
        }
//        String attempt = currentPlant.getPlantPic();
        imageView.setImageDrawable(context.getResources().getDrawable(currentPlant.getPlantPic()));
        Log.i("tag",currentPlant.getPlantName()); //sample message to logcat (for debugging)
        return rowView;
    }
}

