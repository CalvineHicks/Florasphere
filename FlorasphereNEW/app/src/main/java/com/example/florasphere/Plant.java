package com.example.florasphere;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Used to create a Plant object and access individual attributes of plant objects.
 */
public class Plant implements Serializable
{
    private String _plantName;
    private int _plantPic;
    private int _waterFreq;
    private int _lastWatering;
    public enum WaterAmt { LIGHT, MEDIUM, SOAK };
    public enum LightAmt { LOW, PARTIAL, FULL };
    private WaterAmt _waterAmt;
    private LightAmt _lightAmt;
    private String _genInfo;

    public Plant()
    {
        _plantName = "My Plant";
        _plantPic  = 0;
        _waterFreq = 7;
        _lastWatering = 7;
        _waterAmt  = WaterAmt.MEDIUM;
        _lightAmt  = LightAmt.PARTIAL;
        _genInfo   = "";
    }

    public Plant( String name, int image, int wFreq, WaterAmt wAmt, LightAmt lAmt, String info  )
    {
        _plantName = name;
        _plantPic  = image;
        _waterFreq = wFreq;
        _lastWatering = wFreq;
        _waterAmt  = wAmt;
        _lightAmt  = lAmt;
        _genInfo   = info;
    }

    public void waterPlant(){
        Log.i("tag", "watering the "+_plantName);
        _lastWatering = _waterFreq;
    }

    public String getPlantName()
    {
        return _plantName;
    }

    public int getPlantPic()
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

    public int getLastWatering() { return _lastWatering; }

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

    public void setPlantPic( int pic )
    {
        _plantPic = pic;
    }

    public void setWaterFreq( int freq )
    {
        _waterFreq = freq;
    }

    public void setLastWatering( int day ) { _lastWatering = day; }

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