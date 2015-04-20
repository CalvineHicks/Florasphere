package com.example.florasphere;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Jessica on 4/14/2015.
 */
public class UserPlantlist
{
    private ArrayList<Plant> userPlantList;
    private static UserPlantlist instance;

    private Context context;

    private UserPlantlist(Context context)
    {
       userPlantList = new ArrayList<>();
       this.context = context;
       //this is testing of the stuff and things
//       Plant p = new Plant( "Boston Fern", R.drawable.boston_fern , 5, Plant.WaterAmt.MEDIUM, Plant.LightAmt.FULL,
//                "Thrive in humid conditions with bright indirect sunlight, i.e. close to "  +
//                        "east or west facing window. Prefer daytime temp. from 65-75 degrees and "  +
//                        "evening temp. from 55-65 degrees.  Cool evening temps will prevent "       +
//                        "fungus growth. If climate is dry, mist Boston Fern once a day with a "     +
//                        "spray bottle filled with clean tepid water. In summer months, water more " +
//                        "frequently and try not to let soil dry out between waterings. In winter "  +
//                        "months, allow soil to dry out a little before watering, but resume "       +
//                        "regular watering schedule when fronds start to appear. Its pot must be "   +
//                        "clean and have drainage holes in the bottom. " );
//        addPlant(p);
       try {
           SharedPreferences plants = context.getSharedPreferences("plants", 0);
           int numPlants = plants.getInt("numPlants", 0);
           for (int i = 0; i < numPlants; i++) {
              Plant p = (Plant) fromString(plants.getString("plant" + i, ""));
               userPlantList.add(p);
           }
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
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
        try {
            SharedPreferences plants = context.getSharedPreferences("plants", 0);
            int numPlants = plants.getInt("numPlants", 0);
            SharedPreferences.Editor editor = plants.edit();
            String p = toString(plant);
            editor.putString("plant"+numPlants, p);
            editor.putInt("numPlants", numPlants+1);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSize()
    {
        return userPlantList.size();
    }

    public Plant getElement( int index )
    {
        return userPlantList.get( index );
    }

    public Plant[] toArray()
    {
        Plant[] plantArray = new Plant[ getSize() ];
        return userPlantList.toArray( plantArray );
    }

    private static Object fromString( String s ) throws IOException ,
            ClassNotFoundException {
        byte [] data = Base64.decode(s, 0);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    private static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return new String( Base64.encode( baos.toByteArray(), 0 ) );
    }

}
