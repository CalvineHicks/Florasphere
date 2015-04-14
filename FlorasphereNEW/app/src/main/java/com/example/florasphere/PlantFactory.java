/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloraSphere;

//import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
//import javax.imageio.ImageIO;

/**
 * Create and return a default plant object.
 */
public class PlantFactory
{
    private String plantName;
    private int    wateringFrequency;
    private URL    imageURL;
    
    
    public PlantFactory( String plantName )
    {
        this.plantName = plantName;
        wateringFrequency = 7;
        try {
            imageURL = new URL("http://s1379.photobucket.com/user/stereo_collision/media/picture_zpsqrfjbu17.png.html");
            //image = ImageIO.read(url);
        } catch ( MalformedURLException mue ) {
            System.out.println("Error openning file: " + mue.getMessage());
        }     
    }
    
    public String getPlantName()
    {
        return plantName;
    }
    
    public int getWateringFrequency()
    {
        return wateringFrequency;
    }
    
    public URL getPlantImage()
    {
        return imageURL;
    }
    
}
