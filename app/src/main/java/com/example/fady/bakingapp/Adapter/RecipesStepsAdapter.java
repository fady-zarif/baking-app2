package com.example.fady.bakingapp.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fady.bakingapp.DetailsActivity;
import com.example.fady.bakingapp.Fragment.IngredientsFragment;
import com.example.fady.bakingapp.Fragment.RecipeDetailFragment;
import com.example.fady.bakingapp.Model.Recipe;
import com.example.fady.bakingapp.Model.Step;
import com.example.fady.bakingapp.R;
import com.example.fady.bakingapp.RecipesActivity;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Fady on 2017-06-08.
 */

public class RecipesStepsAdapter extends RecyclerView.Adapter<RecipesStepsAdapter.RecipesStepsHolder> {
    Recipe recipe;
    Context context;

    public RecipesStepsAdapter(Recipe recipe, Context context) {
        this.recipe = recipe;
        this.context = context;
    }


    public class RecipesStepsHolder extends RecyclerView.ViewHolder {
        public TextView stepDesc;

        public RecipesStepsHolder(View itemView) {
            super(itemView);
            stepDesc = (TextView) itemView.findViewById(R.id.mytextview);
        }
    }

    @Override
    public RecipesStepsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_style, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        RecipesStepsHolder stepsHolder = new RecipesStepsHolder(view);
        return stepsHolder;
    }

    @Override
    public void onBindViewHolder(RecipesStepsHolder holder, final int position) {
        if (position == 0) {
            holder.stepDesc.setText("Ingredients");
        } else {
            holder.stepDesc.setText("Step " + position + ": " + recipe.getStepArrayList().get(position - 1).getShortDescription());
        }
        holder.stepDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((Activity) context).getFragmentManager();
                Bundle bundle = new Bundle();

                if (position == 0) {
                    IngredientsFragment fragment = new IngredientsFragment();
                    bundle.putParcelableArrayList("myRecipeIngredient", recipe.getIngredientArrayList());
                    fragment.setArguments(bundle);
                    if (DetailsActivity.istablet == true) {
                        manager.beginTransaction().addToBackStack(fragment.getClass().getName())
                                .replace(R.id.myframe2, fragment).commit();
                    } else {
                        manager.beginTransaction().addToBackStack(fragment.getClass().getName())
                                .replace(R.id.myframe, fragment).commit();
                    }


                } else {
                    RecipeDetailFragment detailFragment = new RecipeDetailFragment();
                    bundle.putParcelableArrayList("RecipeStepArraylist", recipe.getStepArrayList());
                    bundle.putInt("Pos", position - 1);
                    detailFragment.setArguments(bundle);
                    if (DetailsActivity.istablet == true) {
                        manager.beginTransaction().addToBackStack("a")
                                .replace(R.id.myframe2, detailFragment, "FragmentB").commit();
                        RecipesActivity.FrNum = 0;

                    } else {
                        manager.beginTransaction().addToBackStack("a")
                                .replace(R.id.myframe, detailFragment, "FragmentB").commit();
                        RecipesActivity.FrNum = 0;

                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipe.getStepArrayList().size() + 1;
    }


}
