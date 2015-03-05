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
public class FlorasphereListArrayAdaptor  extends ArrayAdapter<String>
{
    private final Context context;
    private final String[] values;
    private String username;
    private String snackname;
    private int wateringStatus;

    //private DatabaseHelper dh;

    public FlorasphereListArrayAdaptor(Context context, String[] values	)
    {
        super(context, R.layout.list_plant_list, values);
        this.context = context;
        this.values = values;
        //dh = new DatabaseHelper(context);
    }

        public View getView(int position, View convertView, ViewGroup parent)
    {
          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

          View rowView = inflater.inflate(R.layout.list_plant_list, parent, false);
          TextView textView = (TextView) rowView.findViewById(R.id.plant_name);
          ImageView imageView = (ImageView) rowView.findViewById(R.id.plant_image);
          ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.plant_status);

        Log.i("tag","returning row view"); //sample message to logcat (for debugging)
        return rowView;
    }
}

