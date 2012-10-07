package com.mintplex.oeffioptimizer;

import com.mintplex.oeffioptimizer.model.Location;

import java.util.List;

/*
 * 
 */
public interface LocationStore {
    public boolean isInitialized();
    public Location getLocation(int id);
    public void updateTree(List<Location> list);
}
