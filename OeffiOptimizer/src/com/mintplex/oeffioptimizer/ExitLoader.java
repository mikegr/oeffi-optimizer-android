package com.mintplex.oeffioptimizer;

import android.content.Context;

import com.mintplex.oeffioptimizer.model.Exit;

import java.util.List;

public class ExitLoader extends BetterAsyncTaskLoader<LoaderResult<List<Exit>>> {

    
    private int location;
    
    public ExitLoader(Context context, int location) {
        super(context);
        this.location = location;
    }

    @Override
    public LoaderResult<List<Exit>> loadInBackground() {
        try {
            return new LoaderResult(new GAServer().getExits(location));
        } catch (Exception e) {
            return new LoaderResult<List<Exit>>(e);
        }
        
    }
}
