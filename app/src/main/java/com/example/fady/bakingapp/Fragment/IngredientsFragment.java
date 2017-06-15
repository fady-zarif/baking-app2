package com.example.fady.bakingapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fady.bakingapp.Adapter.IngredientsAdapter;
import com.example.fady.bakingapp.Model.Ingredient;
import com.example.fady.bakingapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends android.app.Fragment {
    RecyclerView ingredientsRecyclerview;

    public IngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ingredientsRecyclerview = (RecyclerView) root.findViewById(R.id.ingredientRecyclerView);
        ArrayList<Ingredient> ingredients = getArguments().getParcelableArrayList("myRecipeIngredient");
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getActivity(), ingredients);
        ingredientsRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        ingredientsRecyclerview.setHasFixedSize(true);
        ingredientsRecyclerview.setAdapter(ingredientsAdapter);

        return root;
    }

}
