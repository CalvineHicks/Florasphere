package com.example.florasphere;

/**
 * Created by madisonrockwell on 3/15/15.
 */
import android.content.Context;
import android.os.Bundle;
import android.app.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.Intent;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SearchSubmissionActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchsubmission);

        final EditText plantSearch = (EditText)findViewById(R.id.search_text_by_name);
        final EditText wateringFrequencySearch = (EditText)findViewById(R.id.search_text_by_watering_frequency);
        final EditText wateringAmountSearch = (EditText)findViewById(R.id.search_text_by_watering_amount);
        final EditText lightAmountSearch = (EditText)findViewById(R.id.search_text_by_light_amount);

        final Context context = this;
        final PlantStorage ps = new PlantStorage(this);

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v) {

                StatePattern statePattern = null;

                Intent k = new Intent(SearchSubmissionActivity.this, SearchResultsActivity.class);


                if(validateNameInput(plantSearch.getText().toString())){
                    if (statePattern == null) {
                        statePattern = new searchPlantNameState();
                    }
                    else{
                        statePattern = new invalidState();
                    }
                }

                if(validateWateringAmountInput(wateringAmountSearch.getText().toString())){
                    if (statePattern == null) {
                        statePattern = new searchPlantWateringAmountState();
                    }
                    else{
                        statePattern = new invalidState();
                    }
                }
                if(validateLightAmountInput(lightAmountSearch.getText().toString())){
                    if (statePattern == null) {
                        statePattern = new searchPlantLightAmountState();
                    }
                    else{
                        statePattern = new invalidState();
                    }
                }

                if(validateNumberInput(wateringFrequencySearch.getText().toString())){
                    if (statePattern == null) {
                        statePattern = new searchPlantWateringFrequencyState();
                    }
                    else{
                        statePattern = new invalidState();
                    }
                }
                if (statePattern == null) {
                    statePattern = new invalidState();
                }
                statePattern.search();

                //Toast.makeText(context, "please input a lowercase plant name with no symbols", Toast.LENGTH_LONG).show();
                //Log.i("tag", "Search button pressed, SearchResultsActivity started");
            }
        });
    }




    protected boolean validateNameInput(String string)
    {
        if(string == null || string.trim().equals("")){
            return false;
        }
        if(Pattern.matches("[A-Z a-z]+", string)){
            return true;
        }
        Toast.makeText(SearchSubmissionActivity.this, "we have you yet to know if a plant name is invalid", Toast.LENGTH_LONG).show();
        return false;


    }

    protected boolean validateWateringAmountInput(String string)
    {
        if(string == null || string.trim().equals("")){
            return false;
        }
        if(string.equals("light") || string.equals("medium") || string.equals("soak")){
            return true;
        }
         Toast.makeText(SearchSubmissionActivity.this, "watering amount only takes options light, medium, and soak", Toast.LENGTH_LONG).show();
        return false;
    }

    protected boolean validateLightAmountInput(String string)
    {
        if(string == null || string.trim().equals("")){
            return false;
        }
        if(string.equals("low") || string.equals("partial") || string.equals("full")){
            return true;
        }
            Toast.makeText(SearchSubmissionActivity.this, "light amount only takes options low, partial, or full", Toast.LENGTH_LONG).show();
        return false;
    }

    protected boolean validateNumberInput(String value)
    {
        int numValue;
        if(value == null || value.trim().equals("")){
            return false;
        }
        if(value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4") ||value.equals("5")
                || value.equals("6") || value.equals("7") || value.equals("8") || value.equals("9")) {
          numValue = Integer.parseInt(value);
        }
        else{
            Toast.makeText(SearchSubmissionActivity.this, "Watering frequency only uses numbers 1-9", Toast.LENGTH_LONG).show();
            return false;
        }

        if(numValue > 0 && numValue < 10){
            return true;
        }
        Toast.makeText(SearchSubmissionActivity.this, "Watering frequency only uses numbers 1-9", Toast.LENGTH_LONG).show();
        return false;
    }

    public interface StatePattern{
        public void search();
    }

    public class searchPlantNameState implements StatePattern {
        public void search(){
            EditText plantSearch = (EditText)findViewById(R.id.search_text_by_name);
            Intent k = new Intent(SearchSubmissionActivity.this, SearchResultsActivity.class);
            String keyIdentifer  = null;
            k.putExtra("STRING_TO_EXTRACT", plantSearch.getText().toString());
            k.putExtra("STATE", "plantNameState");
            startActivity(k);
        }
    }
    public class searchPlantWateringAmountState implements StatePattern {
        public void search(){
            EditText wateringAmountSearch = (EditText)findViewById(R.id.search_text_by_watering_amount);
            Intent k = new Intent(SearchSubmissionActivity.this, SearchResultsActivity.class);
            String keyIdentifer  = null;
            k.putExtra("STRING_TO_EXTRACT", wateringAmountSearch.getText().toString());
            k.putExtra("STATE", "wateringAmountState");
            startActivity(k);
        }
    }

    public class searchPlantWateringFrequencyState implements StatePattern {
        public void search(){
            EditText wateringFrequencySearch = (EditText)findViewById(R.id.search_text_by_watering_frequency);
            Intent k = new Intent(SearchSubmissionActivity.this, SearchResultsActivity.class);
            String keyIdentifer  = null;
            k.putExtra("STRING_TO_EXTRACT", wateringFrequencySearch.getText().toString());
            k.putExtra("STATE", "wateringFrequencyState");
            startActivity(k);
        }
    }

    public class searchPlantLightAmountState implements StatePattern {
        public void search(){
            EditText lightAmountSearch = (EditText)findViewById(R.id.search_text_by_light_amount);
            Intent k = new Intent(SearchSubmissionActivity.this, SearchResultsActivity.class);
            String keyIdentifer  = null;
            k.putExtra("STRING_TO_EXTRACT", lightAmountSearch.getText().toString());
            k.putExtra("STATE", "lightAmountState");
            startActivity(k);
        }
    }

    public class invalidState implements StatePattern {
        public void search(){
            Toast.makeText(SearchSubmissionActivity.this, "please search one field and only one field", Toast.LENGTH_LONG).show();
        }
    }
}
