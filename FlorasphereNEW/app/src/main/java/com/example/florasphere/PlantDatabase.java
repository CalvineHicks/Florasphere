package com.example.florasphere;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Creates a single local database that is located on user's phone.
 */
public class PlantDatabase extends SQLiteOpenHelper
{
    private static PlantDatabase instance = null;
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Plant Database";
    private static SQLiteDatabase.CursorFactory factory = null;
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ("   +
                    "PLANT_NAME" + " TEXT, "      +
                    "PLANT_PIC"  + " HYPERLINK, " +
                    "WATER_FREQ" + " INTEGER, "   +
                    "WATER_AMT"  + " TEXT, "      +
                    "LIGHT_AMT"  + " TEXT, "      +
                    "GEN_INFO"   + " MEMO "       + ");";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private PlantDatabase( Context context )
    {
        super( context, TABLE_NAME, factory, DATABASE_VERSION );
    }

    /*Uses Singleton to create a single instance of database.*/
    public static PlantDatabase getInstance( Context context  )
    {
        if( instance == null )
        {
            instance = new PlantDatabase( context );
        }
        return instance;
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL( TABLE_CREATE );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        // Not sure if I need to implement this class... ??
    }

    public void insertPlant( String plantName, String plantPic, int waterFreq, Plant.WaterAmt wAmt, Plant.LightAmt lAmt, String genInfo )
    {
        ContentValues cv = new ContentValues();
        cv.put( "PLANT_NAME", plantName );
        cv.put( "PLANT_PIC", plantPic );
        cv.put( "WATER_FREQ", waterFreq );
        cv.put( "WATER_AMT", wAmt.toString() );
        cv.put( "LIGHT_AMT", lAmt.toString() );
        cv.put( "GEN_INFO", genInfo );

        getWritableDatabase().insert( TABLE_NAME, null, cv );
    }

    /**
     * (USED BY SEARCH METHODS)
     * Invokes Plant to instantiate desired plant based on plant name and returns plant object.
     */
    public Plant getPlant( String plantName )
    {
        String[] columns = {"PLANT_PIC", "WATER_FREQ", "WATER_AMT", "LIGHT_AMT", "GEN_INFO"};
        Cursor c = getReadableDatabase().query( TABLE_NAME, columns, "PLANT_NAME = " + plantName, null, null, null, null );
        Plant p = null;
        if( c != null )
        {
            p = new Plant();
            p.setPlantName( plantName );
            p.setPlantPic( c.getString(0) );
            p.setWaterFreq( c.getInt(1) );
            p.setWaterAmt( Plant.WaterAmt.valueOf( c.getString(2) ) );
            p.setLightAmt( Plant.LightAmt.valueOf( c.getString(3) ) );
            p.setGenInfo( c.getString(4) );
        }
        return p;
    }

    /**
     * (USED BY SEARCH METHODS)
     * Returns String array of all plant names.
     */
    public String[] getAllPlantNames()
    {
        String[] column = {"PLANT_NAME"};
        Cursor   c      = getReadableDatabase().query( TABLE_NAME, column, null, null, null, null, null );
        String[] plantNames = new String[c.getCount()];
        for( int i = 0; i < c.getCount(); i++ )
        {
            plantNames[i] = c.getString( i );
        }

        return plantNames;
    }


}
