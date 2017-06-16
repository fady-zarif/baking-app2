package com.example.fady.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import com.example.fady.bakingapp.Fragment.DetailsActivityFragment;
import com.example.fady.bakingapp.Model.Ingredient;
import java.util.ArrayList;

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
        if (istablet) {
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
