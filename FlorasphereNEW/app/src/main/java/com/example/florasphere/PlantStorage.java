package com.example.florasphere;

import android.content.Context;

/**
 * This class is to be used by the admin methods and search methods.
 */
public class PlantStorage
{
    private PlantDatabase plantDatabase;

    public PlantStorage( Context context )
    {
        plantDatabase = PlantDatabase.getInstance( context );
    }

    /**
     * (Four plants will be in our database for this project and will be hardcoded here unless
     * a better solution is found that is feasible in the amount of time we have.)
     * Only admin has access to this method to add plants to the main database.
     */
    public void initPlant()
    {
        //Note: change this url to filename if needed: "\res\drawable-hdpi\plant_image.jpg"
        String url  = "https://img1.etsystatic.com/020/0/6834826/il_570xN.474249511_c3c5.jpg";
        this.insertPlant( "Succulent", url , 7, Plant.WaterAmt.SOAK, Plant.LightAmt.FULL,
                     "Keep your succulent in soil and in a pot that allows good drainage. "      +
                     "Growing season is from Spring into Fall. Resting time is from late "       +
                     "Fall to early Spring. During resting time, increase the interval "         +
                     "between watering, and let the potting mixture dry out between watering. "  +
                     "During the growing season, a balanced fertilizer, which has been diluted " +
                     "to 1/4 strength, can be added to the water for each watering." );
    }

    /*Only admin has access to this method to add plants to the main database.*/
    public void insertPlant( String plantName, String plantPic, int waterFreq, Plant.WaterAmt wAmt, Plant.LightAmt lAmt, String genInfo )
    {
        plantDatabase.insertPlant( plantName, plantPic, waterFreq, wAmt, lAmt, genInfo );
    }

    /*Only admin has access to this method to add plants to the main database.*/
    public void insertPlant( Plant plant )
    {
        plantDatabase.insertPlant( plant.getPlantName(), plant.getPlantPic(), plant.getWaterFreq(),
                                   plant.getWaterAmt(), plant.getLightAmt(), plant.getGenInfo() );
    }

    /*Returns plant by name.*/
    public Plant getPlant( String plantName )
    {
        return plantDatabase.getPlant( plantName );
    }

    /*Returns all plants in database.*/
    public Plant[] getAllPlants()
    {
        String[] plantNames = plantDatabase.getAllPlantNames();
        Plant[] plants      = new Plant[plantNames.length];
        for( int i = 0; i < plantNames.length; i++ )
        {
            plants[i] = this.getPlant( plantNames[i] );
        }

        return plants;
    }

}
