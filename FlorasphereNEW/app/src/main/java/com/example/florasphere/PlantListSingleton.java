package com.example.florasphere;

/**
 * Created by calvinehicks on 4/7/15.
 */
public class PlantListSingleton {


    int plant_list_size;

    private static PlantListSingleton instance;
    private PlantListSingleton() {
        Plant plantlist[] = new Plant[plant_list_size];
        plant_list_size = 0;
    }

    public static PlantListSingleton getInstance() {
        if (instance == null ) {
                    instance = new PlantListSingleton();
        }
        return instance;
    }


    public void insert(Plant plant){
        instance.plantlist[plant_list_size] = plant;
        plant_list_size++;
    }
}
