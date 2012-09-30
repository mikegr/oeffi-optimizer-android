
package com.mintplex.oeffioptimizer;

import android.app.Application;

import com.mintplex.oeffioptimizer.model.Location;

import java.util.HashMap;
import java.util.List;

public class OOApplication extends Application implements LocationStore {

    
    @Override
    public boolean isInitialized() {
        return (locationTree != null);
    }
    @Override
    public Location getLocation(int id) {
        return locationTree.get(id);
    }

    HashMap<Integer, Location> locationTree;
    
    @Override
    public void updateTree(List<Location> list) {
        locationTree = new HashMap<Integer, Location>();
        for(Location loc:list)
            _updateTree(loc);
    }
    
    private void _updateTree(Location loc) {
        locationTree.put(loc.id, loc);
        for(Location child:loc.children) {
            _updateTree(child);
        }
    }
}
