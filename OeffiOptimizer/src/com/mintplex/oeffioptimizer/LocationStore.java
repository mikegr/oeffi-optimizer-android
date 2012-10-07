package com.mintplex.oeffioptimizer;

import com.mintplex.oeffioptimizer.model.Location;

import java.util.List;
import java.util.Set;

/*
 * 
 */
public interface LocationStore {
    public boolean isInitialized();
    public Location getLocation(String key);
    public void updateTree(List<Location> list);
    public Set<Location> getRoots();
    public void addLocation(Location loc);
    
}
