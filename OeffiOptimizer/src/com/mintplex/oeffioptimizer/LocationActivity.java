package com.mintplex.oeffioptimizer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.mintplex.oeffioptimizer.model.Location;

import java.util.List;

public class LocationActivity extends OOActivity implements LoaderCallbacks<List<Location>>{

    private static final String LOCATION = "LOCATION";

    public static void create(Context ctx, int location) {
        Intent i = new Intent(ctx, LocationActivity.class);
        Bundle b = new Bundle();
        b.putInt(LOCATION, location);
        ctx.startActivity(i);
    }
    
    private int location;
    private ListView list;
    private ViewSwitcher switcher;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_location);
        this.switcher = (ViewSwitcher) findViewById(R.id.activity_location_switcher);
        this.list = (ListView) findViewById(R.id.activity_location_listView);
        this.location = getIntent().getIntExtra(LOCATION, -1);
        
        if (! app.isInitialized()) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        else {
            initList(app.getLocation(location).children);
        }
    }
    
    private void initList(List<Location> locs) {
        
        list.setAdapter(new ArrayAdapter<Location>(this, R.layout.activity_location_list_item, R.id.activity_location_list_item_text, locs));
        switcher.setDisplayedChild(1);
    }

    @Override
    public Loader<List<Location>> onCreateLoader(int arg0, Bundle arg1) {
        return new LocationLoader(this);
    }
    
    @Override
    public void onLoaderReset(Loader<List<Location>> arg0) {
        //ignore? 
    }
    
    @Override
    public void onLoadFinished(Loader<List<Location>> loader, List<Location> result) {
        app.updateTree(result);
        initList(result);
    }
}
