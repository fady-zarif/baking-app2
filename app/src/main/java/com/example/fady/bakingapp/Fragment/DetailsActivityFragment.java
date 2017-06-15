package com.example.fady.bakingapp.Fragment;

import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.fady.bakingapp.Adapter.RecipesStepsAdapter;
import com.example.fady.bakingapp.DetailsActivity;
import com.example.fady.bakingapp.IngredientWidget;
import com.example.fady.bakingapp.Model.Ingredient;
import com.example.fady.bakingapp.Model.Recipe;
import com.example.fady.bakingapp.Model.Step;
import com.example.fady.bakingapp.R;
import com.google.gson.Gson;

import java.sql.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {
    RecyclerView recipeStepsRecycler;

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_details, container, false);
        recipeStepsRecycler = (RecyclerView) root.findViewById(R.id.details_Recycler);
        Recipe recipe = getActivity().getIntent().getParcelableExtra("RecipeObject");
        getActivity().getActionBar().setTitle(recipe.getName());
        RecipesStepsAdapter stepsAdapter = new RecipesStepsAdapter(recipe, getActivity());
        recipeStepsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recipeStepsRecycler.setAdapter(stepsAdapter);
        DetailsActivity.Myingredients = recipe.getIngredientArrayList();

        Intent updateWidget = new Intent(getActivity(), IngredientWidget.class); // Widget.class is your widget class
        updateWidget.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        PendingIntent pending = PendingIntent.getBroadcast(getActivity(), 0, updateWidget, PendingIntent.FLAG_CANCEL_CURRENT);
        try {
            pending.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }


        return root;
    }

}
