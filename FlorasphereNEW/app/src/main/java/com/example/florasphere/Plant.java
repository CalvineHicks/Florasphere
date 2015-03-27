package com.example.florasphere;

import java.io.Serializable;
import java.net.URL;

/**
 * Used to create a Plant object and access individual attributes of plant objects.
 */
public class Plant implements Serializable
{
    private String _plantName;
    private URL _plantPic;
    private int _waterFreq;
    public enum WaterAmt { LIGHT, MEDIUM, SOAK };
    public enum LightAmt { LOW, PARTIAL, FULL };
    private WaterAmt _waterAmt;
    private LightAmt _lightAmt;
    private String _genInfo;

    public String getPlantName()
    {
        return _plantName;
    }

    public URL getPlantPic()
    {
        return _plantPic;
    }

    public int getWaterFreq()
    {
        return _waterFreq;
    }

    public WaterAmt getWaterAmt()
    {
        return _waterAmt;
    }

    public LightAmt getLightAmt()
    {
        return _lightAmt;
    }

    public String getGenInfo()
    {
        return _genInfo;
    }

    public void setPlantName( String name )
    {
        _plantName = name;
    }

    public void setPlantPic( URL url )
    {
        _plantPic = url;
    }

    public void setWaterFreq( int freq )
    {
        _waterFreq = freq;
    }

    public void setWaterAmt( WaterAmt amt )
    {
        _waterAmt = amt;
    }

    public void setLightAmt( LightAmt amt )
    {
        _lightAmt = amt;
    }

    public void setGenInfo( String info )
    {
        _genInfo = info;
    }

}
