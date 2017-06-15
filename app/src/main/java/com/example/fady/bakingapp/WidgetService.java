package com.example.fady.bakingapp;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Fady on 2017-06-15.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this,intent);
    }
}
