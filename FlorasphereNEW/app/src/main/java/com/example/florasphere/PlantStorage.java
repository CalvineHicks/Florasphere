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
        //Note: change url to filename if needed: "\res\drawable-hdpi\Succulents.jpg"
        //References: http://houseplants.about.com/od/growinghealthyhouseplants/a/SucculentsBasic.htm and http://www.csssj.org/welcome_visitors/basic_culture.html
        String url1  = "https://img1.etsystatic.com/020/0/6834826/il_570xN.474249511_c3c5.jpg";
        this.insertPlant( "Succulent", url1 , 7, Plant.WaterAmt.SOAK, Plant.LightAmt.FULL,
                          "Keep your succulent in soil and in a pot that allows good drainage. "      +
                          "Growing season is from Spring into Fall. Resting time is from late "       +
                          "Fall to early Spring. During resting time, increase the interval "         +
                          "between watering, and let the potting mixture dry out between watering. "  +
                          "During the growing season, a balanced fertilizer, which has been diluted " +
                          "to 1/4 strength, can be added to the water for each watering. Prefer "     +
                          "bright light, i.e. close to south-facing window. Prefer daytime temp. "    +
                          "from 70-85 degrees and nighttime temp from 50-55 degrees.  Place outside " +
                          "in the summer day and night if temperature and climate ideal!" );

        //Note: change url to filename if needed: "\res\drawable-hdpi\AfricanViolet.jpg"
        //Reference: http://www.houseplant411.com/houseplant/african-violet-plant
        String url2  = "http://www.houseplant411.com/wp-content/uploads/2007-04-20Saintpaulia_ionantha04-256x190.jpg";
        this.insertPlant( "African Violet", url2 , 8, Plant.WaterAmt.MEDIUM, Plant.LightAmt.FULL,
                          "Allow the top 1-2 inches of soil in an African Violet plant to dry out "   +
                          "before watering. Avoid using water on an African Violet plant that has "   +
                          "passed through a softener or water containing chemicals. Always water "    +
                          "close to the soil to prevent water from getting on the leaves and remove " +
                          "any excess water in the drainage plate after 15 min. Prefer full bright  " +
                          "light, i.e. close to an East facing window. Prefer temperatures between "  +
                          "75-80 degrees during the day and about 10 degrees cooler at night. Keep "  +
                          "away from cold drafts and heating vents. Keep root bound in a small pot "  +
                          "to encourage greater flower production." );

        //Note: change url to filename if needed: "\res\drawable-hdpi\BostonFern"
        //References: http://www.doityourself.com/stry/bostonferns#b and http://www.wikihow.com/Care-for-Boston-Ferns
        String url3  = "http://www.guide-to-houseplants.com/image-files/boston-fern-plant.jpg";
        this.insertPlant( "Boston Fern", url3, 5, Plant.WaterAmt.MEDIUM, Plant.LightAmt.FULL,
                          "Thrive in humid conditions with bright indirect sunlight, i.e. close to "  +
                          "east or west facing window. Prefer daytime temp. from 65-75 degrees and "  +
                          "evening temp. from 55-65 degrees.  Cool evening temps will prevent "       +
                          "fungus growth. If climate is dry, mist Boston Fern once a day with a "     +
                          "spray bottle filled with clean tepid water. In summer months, water more " +
                          "frequently and try not to let soil dry out between waterings. In winter "  +
                          "months, allow soil to dry out a little before watering, but resume "       +
                          "regular watering schedule when fronds start to appear. Its pot must be "   +
                          "clean and have drainage holes in the bottom. " );

        //Note: change url to filename if needed: "\res\drawable-hdpi\BostonFern"
        //References: http://www.guide-to-houseplants.com/english-ivy.html and http://www.ivy.org/about_bv8.htm
        String url4  = "http://www.sparkpeople.com/news/genericpictures/english_ivy_plant.jpg";
        this.insertPlant( "English Ivy", url4, 6, Plant.WaterAmt.MEDIUM, Plant.LightAmt.FULL,
                          "Thrive in humid conditions with bright indirect sunlight, i.e. close to "  +
                          "east or west facing window. Prefer temp. from 50-70 degrees. If dry "      +
                          "climate, mist English Ivy once a day with a spray bottle filled with "     +
                          "clean tepid water. In summer months, water more frequently to maintain "   +
                          "evenly moist soil. In winter, allow soil to dry out a little before "      +
                          "watering. Use dirt-free potting mix or any that provide good drainage. " );
    }

    /*Only admin has access to this method to add plants to the main database.*/
    public void insertPlant( String plantName, String plantPic, int waterFreq, Plant.WaterAmt wAmt, Plant.LightAmt lAmt, String genInfo )
    {
        plantDatabase.insertPlant( plantName, plantPic, waterFreq, wAmt, lAmt, genInfo );
    }

    /*Used in plant search to find & select specific plants in the main database to add to user's plant list.*/
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
