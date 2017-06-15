package com.example.fady.bakingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fady.bakingapp.Model.Ingredient;
import com.example.fady.bakingapp.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Fady on 2017-05-12.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.myViewHolder> {
    public Context context;
    public ArrayList<Ingredient> ingredientArrayList;

    public IngredientsAdapter(Context context, ArrayList<Ingredient> ingredients) {
        this.context = context;
        this.ingredientArrayList = ingredients;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredient_Textview, measure_textView, quantity_Textview;

        public myViewHolder(View itemView) {
            super(itemView);
            ingredient_Textview = (TextView) itemView.findViewById(R.id.ingredient);
            measure_textView = (TextView) itemView.findViewById(R.id.measure);
            quantity_Textview = (TextView) itemView.findViewById(R.id.quantity);
        }
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.ingredient_card, null);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.ingredient_Textview.setText(ingredientArrayList.get(position).getIngredient());
        holder.measure_textView.setText(ingredientArrayList.get(position).getMeasure());
        holder.quantity_Textview.setText(String.valueOf(ingredientArrayList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return ingredientArrayList.size();
    }


}
