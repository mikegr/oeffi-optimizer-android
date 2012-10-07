
package com.mintplex.oeffioptimizer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.mintplex.oeffioptimizer.AddLocationFragment.EditNameDialogListener;
import com.mintplex.oeffioptimizer.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends OOActivity implements
        EditNameDialogListener,
        LoaderCallbacks<LoaderResult<List<Location>>> {

    private static final String LEVEL = "LEVEL";
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

    
    public static void start(Context ctx, int level, int location) {
        Intent i = new Intent(ctx, LocationActivity.class);
        i.putExtra(LEVEL, level);
        i.putExtra(LOCATION, location);
        ctx.startActivity(i);
    }
    
    private int level;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_location);
        this.switcher = (ViewSwitcher) findViewById(R.id.activity_location_switcher);
        this.list = (ListView) findViewById(R.id.activity_location_listView);
        this.level = getIntent().getIntExtra(LEVEL, 0);
        this.location = getIntent().getIntExtra(LOCATION, -1);

        if (!app.isInitialized()) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        else {
            initList(app.getLocation(location).children);
        }
        
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Location loc = (Location) arg0.getItemAtPosition(arg2);
                if (level == 5) {
                    ExitActivity.start(LocationActivity.this, loc.id);
                }
                else {
                    LocationActivity.start(LocationActivity.this, level+1,  loc.id);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_location, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            showAddLocationDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddLocationDialog() {
        FragmentManager fm = getSupportFragmentManager();
        AddLocationFragment f = new AddLocationFragment();
        f.show(fm, "ADD_LOCATION");
    }

    @Override
    public void onFinishEditDialog(String inputText) {

        new AsyncTask<String, Void, AsyncTaskResult<Integer>>() {

            @Override
            protected AsyncTaskResult<Integer> doInBackground(String... params) {
                try {
                    return new AsyncTaskResult<Integer>(new GAServer().addLocation(new Location(params[0])));
                } catch (Exception e) {
                    return new AsyncTaskResult<Integer>(e);
                }
            }

            @Override
            protected void onPreExecute() {
                setSupportProgressBarIndeterminateVisibility(true);
            }

            protected void onPostExecute(AsyncTaskResult<Integer> result) {
                setSupportProgressBarIndeterminateVisibility(false);
                if (result.hasError()) {
                    Toast.makeText(LocationActivity.this, "Fehlgeschlagen: " + result.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            };

        }.execute(inputText);

    }

    private void initList(List<Location> locs) {
        list.setAdapter(new ArrayAdapter<Location>(this, R.layout.activity_location_list_item,
                R.id.activity_location_list_item_text, locs));
        switcher.setDisplayedChild(1);
    }

    @Override
    public Loader<LoaderResult<List<Location>>> onCreateLoader(int arg0, Bundle arg1) {
        return new LocationLoader(this);
    }

    @Override
    public void onLoaderReset(Loader<LoaderResult<List<Location>>> arg0) {
        // ignore?
    }

    @Override
    public void onLoadFinished(Loader<LoaderResult<List<Location>>> loader, 
            LoaderResult<List<Location>> result) {
        if (result.hasError()) {
            Toast.makeText(this, "Fehlgeschlagen: " + result.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
        else {
            app.updateTree(result.getResult());
            app.getLocation(location);
        }
    }
}
