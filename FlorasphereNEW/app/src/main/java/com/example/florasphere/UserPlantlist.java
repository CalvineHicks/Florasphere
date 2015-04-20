package com.example.florasphere;

import java.util.ArrayList;

/**
 * Created by Jessica on 4/14/2015.
 */
public class UserPlantlist
{
    private ArrayList<Plant> userPlantList;
    private static UserPlantlist instance;

//    String FILENAME = "user_plant_list.txt";
//    String string = "hello world!";
//
//    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//    fos.write(string.getBytes());
//    fos.close();

    private UserPlantlist()
    {
        userPlantList = new ArrayList<>();
    }

    /*Uses Singleton to create a single instance of user's plantlist.*/
    public static UserPlantlist getInstance()
    {
        if( instance == null )
        {
            instance = new UserPlantlist();
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

    public Plant[] toArray()
    {
        Plant[] plantArray = new Plant[getSize()];
        return userPlantList.toArray( plantArray );
    }

}
