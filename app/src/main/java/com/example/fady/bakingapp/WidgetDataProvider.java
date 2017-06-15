package com.example.fady.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.fady.bakingapp.Adapter.IngredientsAdapter;
import com.example.fady.bakingapp.Model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Fady on 2017-06-15.
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    ArrayList<String> arrayList = new ArrayList<>();
    Context context;
    Intent intent;

    private void init() {
        arrayList.clear();
        try {
            for (int i = 0; i < DetailsActivity.Myingredients.size(); i++) {
                String Ingre = DetailsActivity.Myingredients.get(i).getIngredient() + "\n" + "Measure: " + DetailsActivity.Myingredients.
                        get(i).getMeasure() + "\n" + "Quantity: " + DetailsActivity.Myingredients.get(i).getQuantity();
                arrayList.add(Ingre);
            }
        } catch (Exception e) {
        }
    }

    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCreate() {
        init();
    }

    @Override
    public void onDataSetChanged() {
        init();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.text_template);
        remoteViews.setTextViewText(R.id.mytemplate, arrayList.get(position));
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
