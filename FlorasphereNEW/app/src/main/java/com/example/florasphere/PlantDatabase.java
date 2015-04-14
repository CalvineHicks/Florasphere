package com.example.florasphere;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Creates a single local database that is located on user's phone.
 */
public class PlantDatabase extends SQLiteOpenHelper
{
    private static PlantDatabase instance = null;
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "PlantDatabase";
    private static SQLiteDatabase.CursorFactory factory = null;
/*    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + "("    +
                        "PLANT_NAME  TEXT, "      +
                        "PLANT_PIC   HYPERLINK, " +
                        "WATER_FREQ  INTEGER, "   +
                        "WATER_AMT   TEXT, "      +
                        "LIGHT_AMT   TEXT, "      +
                        "GEN_INFO    MEMO "       + ");"; */
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + "("    +
                    "PLANT_NAME  TEXT, "      +
                    "PLANT_PIC   TEXT, " +
                    "WATER_FREQ  INTEGER, "   +
                    "WATER_AMT   TEXT, "      +
                    "LIGHT_AMT   TEXT, "      +
                    "GEN_INFO    TEXT "       + ");";

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
        //Plant p = null;
        Plant p = getPlant( plantName );

        ContentValues cv = new ContentValues();
        Log.i("tag", "insertPlant(): Setting up content values.");
        cv.put( "PLANT_NAME", plantName );
        cv.put( "PLANT_PIC", plantPic );
        cv.put( "WATER_FREQ", waterFreq );
        cv.put( "WATER_AMT", wAmt.toString() );
        cv.put( "LIGHT_AMT", lAmt.toString() );
        cv.put( "GEN_INFO", genInfo );

        if( p == null )
        {
            Log.i("tag", "insertPlant():Inserting into database");
            try
            {
                if (getWritableDatabase().insert(TABLE_NAME, null, cv) == -1)
                {
                    String msg = "insertPlant(): insert() failed.";
                    Log.i("tag", msg);
                    throw new SQLiteException(msg);
                }
            }
            catch (SQLiteException se)
            {
                Log.i("tag", "insertPlant(): getWritableDatabase() failed - " + se.getMessage());
                throw se;
            }
        }
        else
        {
            Log.i("tag", "insertPlant(): Replacing plant into database");
            try
            {
                if (getWritableDatabase().replace(TABLE_NAME, null, cv) == -1)
                {
                    String msg = "insertPlant(): replace() failed.";
                    Log.i("tag", msg);
                    throw new SQLiteException(msg);
                }
            }
            catch (SQLiteException se)
            {
                Log.i("tag", "insertPlant(): getWritableDatabase() failed 2 - " + se.getMessage());
                throw se;
            }
        }

        Log.i("tag", "insertPlant(): finished.");
    }

    /**
     * (USED BY SEARCH METHODS)
     * Invokes Plant to instantiate desired plant based on plant name and returns plant object.
     */
    public Plant getPlant( String plantName )
    {
        String[] columns = {"PLANT_PIC", "WATER_FREQ", "WATER_AMT", "LIGHT_AMT", "GEN_INFO"};
        Plant p          = null;
        try
        {
            SQLiteDatabase db = getReadableDatabase();

            try
            {
                Cursor c = db.query(TABLE_NAME, columns, "PLANT_NAME = \"" + plantName + "\"", null, null, null, null);

                if (c != null)
                {

                    for( int i = 0; i < c.getCount(); i++ )
                    {
                        //c.moveToFirst();
                        p = new Plant();
                        p.setPlantName(plantName);
                        p.setPlantPic(c.getString(0));
                        p.setWaterFreq(c.getInt(1));
                        p.setWaterAmt(Plant.WaterAmt.valueOf(c.getString(2)));
                        p.setLightAmt(Plant.LightAmt.valueOf(c.getString(3)));
                        p.setGenInfo(c.getString(4));
                    }
                }
            }
            catch( SQLiteException se )
            {
                Log.i( "tag", "PlantDatabase.getPlant(): query() - plant " + plantName + " does not exist: " + se.getMessage() );
            }
        }
        catch( SQLiteException se )
        {
            Log.i( "tag", "PlantDatabase.getPlant(): getReadableDatabase() failed: " + se.getMessage() );
            throw se;
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
        String[] plantNames = null;

        try
        {
            Log.i( "tag", "PlantDatabase.getAllPlantNames(): Starting query: " );
            Cursor c = getReadableDatabase().query(TABLE_NAME, column, null, null, null, null, null);
            Log.i( "tag", "PlantDatabase.getAllPlantNames(): Finished query: " );

            int count = c.getCount();

            Log.i( "tag", "PlantDatabase.getAllPlantNames(): count = " + count + ", column count = " + c.getColumnCount() );

            if( count > 0 )
            {
                plantNames = new String[count];

                c.moveToFirst();

                for (int i = 0; i < count; i++)
                {
                    plantNames[i] = c.getString(0);
                    Log.i("tag", "PlantDatabase.getAllPlantNames(): " + i + ") query returned " + plantNames[i]);
                }
            }
            else
            {
                Log.i( "tag", "PlantDatabase.getAllPlantNames(): No plants found in the database." );
            }
            c.close();
        }
        catch( SQLiteException se )
        {
            Log.i( "tag", "PlantDatabase.getAllPlantNames(): getReadableDatabase() Failed: " + se.getMessage() );
            throw se;
        }
        catch( Exception e )
        {
            Log.i( "tag", "PlantDatabase.getAllPlantNames(): Unhandled Exception: " + e.getMessage() );
            throw e;
        }

        return plantNames;
    }


}
