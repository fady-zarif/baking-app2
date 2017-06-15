package com.example.fady.bakingapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fady.bakingapp.Adapter.RecipesAdapter;
import com.example.fady.bakingapp.Model.Ingredient;
import com.example.fady.bakingapp.Model.Recipe;
import com.example.fady.bakingapp.Model.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipesActivity extends Activity {
    ArrayList<Recipe> RecipeArrayList;
    ArrayList<Ingredient> ingredientArrayList;
    ArrayList<Step> stepArrayList;
    RecyclerView recipeRecyclerview;
    public static int FrNum = 0;
    private final String TAG = "RECIPE_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        RecipeArrayList = new ArrayList();

        final RecipesAdapter recipesAdapter = new RecipesAdapter(RecipeArrayList, this);
        recipeRecyclerview = (RecyclerView) findViewById(R.id.RecipesRecyclerView);
        recipeRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        recipeRecyclerview.setHasFixedSize(true);
        recipeRecyclerview.setAdapter(recipesAdapter);
        retrieveJsonData(recipesAdapter);
    }

    public void retrieveJsonData(final RecipesAdapter recipesAdapter) {
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("Hello1", String.valueOf(response.length()));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jb = (JSONObject) response.get(i);
                        JSONArray ingredientArray = jb.getJSONArray("ingredients");
                        JSONArray stepsArray = jb.getJSONArray("steps");
                        ingredientArrayList = new ArrayList<>();
                        stepArrayList = new ArrayList<>();
                        for (int o = 0; o < ingredientArray.length(); o++) {
                            JSONObject ingredientObj = (JSONObject) ingredientArray.get(o);
                            int quantity = ingredientObj.getInt("quantity");
                            String measure = ingredientObj.getString("measure");
                            String ingredient = ingredientObj.getString("ingredient");
                            Ingredient myIngredient = new Ingredient(quantity, measure, ingredient);
                            ingredientArrayList.add(myIngredient);
                        }
                        for (int j = 0; j < stepsArray.length(); j++) {
                            JSONObject stepsObj = (JSONObject) stepsArray.get(j);
                            String shortDescription = stepsObj.getString("shortDescription");
                            String description = stepsObj.getString("description");
                            String videoURL = stepsObj.getString("videoURL");
                            String thumbnailURL = stepsObj.getString("thumbnailURL");
                            Step mysteps = new Step(shortDescription, description, videoURL, thumbnailURL);
                            stepArrayList.add(mysteps);
                        }
                        String name = jb.getString("name");
                        int id = jb.getInt("id");
                        Recipe recipe = new Recipe(id, name, ingredientArrayList, stepArrayList);
                        RecipeArrayList.add(recipe);
                        recipesAdapter.notifyDataSetChanged();
                        Log.d("Recype", String.valueOf(RecipeArrayList.get(i).getStepArrayList().size()));
                    } catch (JSONException e) {
                        Log.d(TAG, "ERRORMESSAGE" + e.getLocalizedMessage());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("WHY", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(request);

    }

}
