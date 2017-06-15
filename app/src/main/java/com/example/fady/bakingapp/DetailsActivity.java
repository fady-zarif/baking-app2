package com.example.fady.bakingapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ArrayAdapter;

import com.example.fady.bakingapp.Fragment.DetailsActivityFragment;
import com.example.fady.bakingapp.Model.Ingredient;
import com.example.fady.bakingapp.Model.Recipe;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DetailsActivity extends Activity {
    public static boolean istablet = false;
    public static ArrayList<Ingredient> Myingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        DetailsActivityFragment fragment = new DetailsActivityFragment();
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.myframe, fragment);
        fragmentTransaction.commit();


        if (findViewById(R.id.myframe2) != null) {
            istablet = true;
        }


    }

    @Override
    public void onBackPressed() {
        if (istablet == true) {
            startActivity(new Intent(DetailsActivity.this, RecipesActivity.class));
            return;
        }
        if (RecipesActivity.FrNum != 0) {
            while (RecipesActivity.FrNum > 0) {
                getFragmentManager().popBackStack("a",
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
                RecipesActivity.FrNum--;
            }
        } else {
            super.onBackPressed();
        }
    }
}
