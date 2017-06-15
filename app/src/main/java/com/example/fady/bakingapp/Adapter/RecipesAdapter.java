package com.example.fady.bakingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fady.bakingapp.DetailsActivity;
import com.example.fady.bakingapp.Model.Recipe;
import com.example.fady.bakingapp.R;

import java.util.ArrayList;

/**
 * Created by Fady on 2017-05-11.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.myViewHolder> {
    ArrayList<Recipe> recipes;
    Context context;
    int[] Imagearray = {R.drawable.nutella, R.drawable.brownie, R.drawable.yellowcake};

    public RecipesAdapter(ArrayList<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeName;
        public ImageView recipeImage;
        public LinearLayout rCard;

        public myViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView) itemView.findViewById(R.id.recipeName);
            recipeImage = (ImageView) itemView.findViewById(R.id.recipeImage);
            rCard = (LinearLayout) itemView.findViewById(R.id.R_card);
        }
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, null);
        root.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        myViewHolder holder = new myViewHolder(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.recipeName.setText(recipes.get(position).getName());
        try {
            holder.recipeImage.setImageResource(Imagearray[position]);
        } catch (Exception ex) {
            holder.recipeImage.setImageResource(R.drawable.desert_gen);
            holder.recipeImage.setAlpha(180);
        }
        holder.rCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                Log.e("EWEGMIK", String.valueOf(recipes.get(position).getIngredientArrayList().size()));
                intent.putExtra("RecipeObject", recipes.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

}
