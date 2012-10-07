
package com.mintplex.oeffioptimizer;

import com.mintplex.oeffioptimizer.model.Exit;
import com.mintplex.oeffioptimizer.model.Location;

import java.util.List;

public interface IServer {

    public abstract List<Location> getLocations() throws Exception;

    public abstract Location addLocation(Location loc) throws Exception;

    public abstract List<Exit> getExits(String location) throws Exception;

    public abstract Exit addExit(Exit exit) throws Exception;

}
