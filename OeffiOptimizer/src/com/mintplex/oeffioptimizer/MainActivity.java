package com.mintplex.oeffioptimizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.mintplex.oeffioptimizer.model.Exit;
import com.mintplex.oeffioptimizer.model.Location;

import java.util.List;

public class MainActivity extends OOActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                //test();        
            }
        }).start();
    }

    public void onClick() {
        LocationActivity.start(this, 0, null);
    }

    private void test() {
        GAServer s = new GAServer(this);
        Location loc = new Location("Wien");
        try {
            s.addLocation(loc);
            Log.v("New Id: " + loc.key);
            
            List<Location> locs = s.getLocations();
            for(Location l:locs) {
                Log.v(l.key + " : " +l.name);
            }
            app.updateTree(locs);
           
            Exit exit = new Exit(loc.key, "U5", "Vorne");
            s.addExit(exit);
            
            List<Exit> exits = s.getExits(loc.key);
            
            for(Exit e:exits) {
                Log.v("E:" + e.id + " - "+ e.name + " - " + e.hint);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
