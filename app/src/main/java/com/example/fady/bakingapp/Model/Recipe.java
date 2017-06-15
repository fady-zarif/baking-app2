package com.example.fady.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Fady on 2017-05-11.
 */

public class Recipe implements Parcelable {

    private int id;
    private String name;
    private ArrayList<Ingredient> ingredientArrayList;
    private ArrayList<Step> stepArrayList;

    public Recipe(int id, String name, ArrayList<Ingredient> ingredientArrayList, ArrayList<Step> stepArrayList) {
        this.id = id;
        this.name = name;
        this.ingredientArrayList = ingredientArrayList;
        this.stepArrayList = stepArrayList;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredientArrayList = in.readArrayList(Ingredient.class.getClassLoader());
        stepArrayList = in.readArrayList(Step.class.getClassLoader());
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredientArrayList() {
        return ingredientArrayList;
    }

    public ArrayList<Step> getStepArrayList() {
        return stepArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeList(ingredientArrayList);
        dest.writeList(stepArrayList);
    }
}
