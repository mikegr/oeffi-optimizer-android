
package com.mintplex.oeffioptimizer;

import com.mintplex.oeffioptimizer.model.Exit;
import com.mintplex.oeffioptimizer.model.Location;

import java.util.List;

public interface IServer {

    public abstract List<Location> getLocations() throws Exception;

    public abstract Integer addLocation(Location loc) throws Exception;

    public abstract List<Exit> getExits(int location) throws Exception;

    public abstract void addExit(Exit exit) throws Exception;

}
