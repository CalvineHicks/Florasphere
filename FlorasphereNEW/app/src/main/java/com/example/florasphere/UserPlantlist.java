package com.example.florasphere;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Jessica on 4/14/2015.
 */
public class UserPlantlist
{
    private ArrayList<Plant> userPlantList;
    private static UserPlantlist instance;

    private UserPlantlist(Context context)
    {
       userPlantList = new ArrayList<>();
       //this is testing of the stuff and things
       Plant p = new Plant( "Boston Fern", R.drawable.boston_fern , 5, Plant.WaterAmt.MEDIUM, Plant.LightAmt.FULL,
                "Thrive in humid conditions with bright indirect sunlight, i.e. close to "  +
                        "east or west facing window. Prefer daytime temp. from 65-75 degrees and "  +
                        "evening temp. from 55-65 degrees.  Cool evening temps will prevent "       +
                        "fungus growth. If climate is dry, mist Boston Fern once a day with a "     +
                        "spray bottle filled with clean tepid water. In summer months, water more " +
                        "frequently and try not to let soil dry out between waterings. In winter "  +
                        "months, allow soil to dry out a little before watering, but resume "       +
                        "regular watering schedule when fronds start to appear. Its pot must be "   +
                        "clean and have drainage holes in the bottom. " );
        addPlant(p);

    }

    /*Uses Singleton to create a single instance of user's plantlist.*/
    public static UserPlantlist getInstance(Context context)
    {
        if( instance == null )
        {
            instance = new UserPlantlist(context);
        }
        return instance;
    }

    public void addPlant( Plant plant )
    {
        userPlantList.add( plant );
    }

    public int getSize()
    {
        return userPlantList.size();
    }

    public Plant getElement( int index )
    {
        return userPlantList.get( index );
    }

    public Plant[] toArray( )
    {
        Plant[] plantArray = new Plant[userPlantList.size()];
        return userPlantList.toArray( plantArray );
    }

}
