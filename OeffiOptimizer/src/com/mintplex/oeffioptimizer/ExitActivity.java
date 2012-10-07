
package com.mintplex.oeffioptimizer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.mintplex.oeffioptimizer.AddExitFragment.EnterExitFinished;
import com.mintplex.oeffioptimizer.model.Exit;
import com.mintplex.oeffioptimizer.model.Location;

import java.util.ArrayList;
import java.util.List;

public class ExitActivity extends OOActivity implements LoaderCallbacks<LoaderResult<List<Exit>>>,
        EnterExitFinished {

    private final class ExitAdapter extends ArrayAdapter<Exit> {
        private ExitAdapter(Context context, List<Exit> list) {
            super(context, R.layout.activity_exit_list_item, R.id.activity_exit_list_item_name,  list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            Exit exit = getItem(position);
            Utils.t(view, R.id.activity_exit_list_item_name, exit.name);
            Utils.t(view, R.id.activity_exit_list_item_hint, exit.hint);
            return view;
        }
    }

    private static final String LOCATION = "location";

    private Location location;
    private ListView listView;

    public static void start(Context ctx, String key) {
        ctx.startActivity(new Intent(ctx, ExitActivity.class).putExtra(LOCATION, key));
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        location = app.getLocation(getIntent().getStringExtra(LOCATION));
        setContentView(R.layout.activity_exit);
        listView = (ListView) findViewById(R.id.activity_exit_list);
        getSupportLoaderManager().initLoader(0, null, this);

        View header = getLayoutInflater().inflate(R.layout.activity_exit_list_header, null);
        Utils.t(header, R.id.activity_exit_list_header_station, location.name);

        Location dirLoc = app.getLocation(location.getParent());
        Utils.t(header, R.id.activity_exit_list_header_direction, dirLoc.name);

        Location transLoc = app.getLocation(dirLoc.getParent());
        Utils.t(header, R.id.activity_exit_list_header_transport, transLoc.name);

        Location typeLoc = app.getLocation(transLoc.getParent());
        Utils.t(header, R.id.activity_exit_list_header_type, typeLoc.name);

        Location townLoc = app.getLocation(typeLoc.getParent());
        Utils.t(header, R.id.activity_exit_list_header_town, townLoc.name);

        listView.addHeaderView(header);
    }

    @Override
    public Loader<LoaderResult<List<Exit>>> onCreateLoader(int arg0, Bundle arg1) {
        return new ExitLoader(this, location.key);
    }

    @Override
    public void onLoaderReset(Loader<LoaderResult<List<Exit>>> arg0) {
    }

    @Override
    public void onLoadFinished(Loader<LoaderResult<List<Exit>>> arg0, LoaderResult<List<Exit>> arg1) {
        if (arg1.hasError()) {
            Toast.makeText(this, "Fehlgeschlagen: " + arg1.getError().getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        else {
            initList(arg1.getResult());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_exits, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            new AddExitFragment().show(getSupportFragmentManager(), AddExitFragment.FRAGMENT_TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void enterExitFinished(String name, String hint) {
        Exit exit = new Exit(location.key, name, hint);
        new AsyncTask<Exit, Void, AsyncTaskResult<Exit>>() {

            @Override
            protected AsyncTaskResult<Exit> doInBackground(Exit... params) {
                try {
                    Exit e = params[0];
                    new GAServer().addExit(e);
                    return new AsyncTaskResult<Exit>(e);
                } catch (Exception e) {
                    return new AsyncTaskResult<Exit>(e);
                }
            }

            protected void onPreExecute() {
                setSupportProgressBarIndeterminateVisibility(true);
            };

            protected void onPostExecute(com.mintplex.oeffioptimizer.AsyncTaskResult<Exit> result) {
                setSupportProgressBarIndeterminateVisibility(false);
                if (result.hasError()) {
                    Toast.makeText(ExitActivity.this,
                            "Fehlgeschlagen: " + result.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                else {
                    addExitToList(result.getResult());
                }
            }

        }.execute(exit);
    }

    private void addExitToList(Exit result) {
        //exits.add(result);
        //initList(exits);
        adapter.add(result);
    };

    private List<Exit> exits = new ArrayList<Exit>();

    ExitAdapter adapter; 
    
    private void initList(List<Exit> list) {
        adapter = new ExitAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
