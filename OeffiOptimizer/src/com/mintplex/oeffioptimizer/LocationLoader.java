package com.mintplex.oeffioptimizer;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.mintplex.oeffioptimizer.model.Location;

import java.util.List;

public class LocationLoader extends BetterAsyncTaskLoader<List<Location>> {

    public LocationLoader(Context context) {
        super(context);
    }

    @Override
    public List<Location> loadInBackground() {
        return new Server().getLocations();
    }    
    
}
