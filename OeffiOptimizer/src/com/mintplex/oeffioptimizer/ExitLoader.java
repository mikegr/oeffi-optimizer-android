package com.mintplex.oeffioptimizer;

import android.content.Context;

import com.mintplex.oeffioptimizer.model.Exit;

import java.util.List;

public class ExitLoader extends BetterAsyncTaskLoader<LoaderResult<List<Exit>>> {

    
    private String location;
    
    public ExitLoader(Context context, String location) {
        super(context);
        this.location = location;
    }

    @Override
    public LoaderResult<List<Exit>> loadInBackground() {
        try {
            return new LoaderResult(new GAServer(getContext()).getExits(location));
        } catch (Exception e) {
            return new LoaderResult<List<Exit>>(e);
        }
        
    }
}
