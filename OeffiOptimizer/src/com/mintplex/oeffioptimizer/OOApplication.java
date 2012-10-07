
package com.mintplex.oeffioptimizer;

import android.app.Application;

import com.mintplex.oeffioptimizer.model.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OOApplication extends Application implements LocationStore {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.setTAG("OeffiOptimizer");
        Log.setLogLevel(android.util.Log.VERBOSE);
    }

    boolean initalized = false;
    @Override
    public boolean isInitialized() {
        return initalized;
    }

    @Override
    public Location getLocation(String key) {
        return locationTree.get(key);
    }

    HashMap<String, Location> locationTree = new HashMap<String, Location>();
    
    Set<Location> roots = new HashSet<Location>();

    @Override
    public void updateTree(List<Location> list) {
        initalized = true;
        locationTree.clear();
        roots.clear();
        for (Location loc : list)
            _updateTree(loc);
    }

    private void _updateTree(Location loc) {
        Location fromMap = locationTree.get(loc.key);
        if (fromMap != null) {
            loc.children = fromMap.children;
        }
        locationTree.put(loc.key, loc);

        if (loc.parent == null) {
            roots.add(loc);
        }
        else {
            Location parent = locationTree.get(loc.parent);
            if (parent == null) {
                parent = new Location();
            }
            parent.children.add(loc);
        }
    }
    
    @Override
    public Set<Location> getRoots() {
        return roots;
    }
    @Override
    public void addLocation(Location loc) {
        _updateTree(loc);
    }
}
