package com.mintplex.oeffioptimizer;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.mintplex.oeffioptimizer.model.Location;

import java.util.List;

public class LocationLoader extends BetterAsyncTaskLoader<LoaderResult<List<Location>>> {

    
    public LocationLoader(Context context) {
        super(context);
    }

    @Override
    public LoaderResult<List<Location>> loadInBackground() {
        try {
            return new LoaderResult<List<Location>>(new GAServer().getLocations());
        } catch (Exception e) {
            return new LoaderResult<List<Location>>(e);
        }
    }
    
}
