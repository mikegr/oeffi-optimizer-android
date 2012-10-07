package com.mintplex.oeffioptimizer;

import com.mintplex.oeffioptimizer.model.Exit;
import com.mintplex.oeffioptimizer.model.Location;

import java.util.ArrayList;
import java.util.List;

public class Server implements IServer {

    /* (non-Javadoc)
     * @see com.mintplex.oeffioptimizer.IServer#getLocations()
     */
    @Override
    public List<Location> getLocations(){
        ArrayList<Location> result = new ArrayList<Location>();
        Location root = new Location(1, "Wien");
        Location ubahn = new Location(2, "U-Bahn");
        Location u3 = new Location(2, "U3");
        Location ottakring = new Location(2, "Richtung Ottakring");
        Location volkstheater = new Location(2, "Volkstheater");
        
        root.add(ubahn);
        ubahn.add(u3);
        u3.add(ottakring);
        ottakring.add(volkstheater);
        
        result.add(root);
        return result;
        
    }
    
    /* (non-Javadoc)
     * @see com.mintplex.oeffioptimizer.IServer#addLocation(com.mintplex.oeffioptimizer.model.Location)
     */
    @Override
    public Integer addLocation(Location loc) {
        // do not transfer
        return null;
    }
    
    /* (non-Javadoc)
     * @see com.mintplex.oeffioptimizer.IServer#getExits(int)
     */
    @Override
    public List<Exit> getExits(int location) {
        List<Exit> result = new ArrayList<Exit>();
        Exit exit1 = new Exit(5, 1, "U2", "Ganz vorne");
        Exit exit2 = new Exit(5, 2, "Volksgarten", "Ganz hinten");
        result.add(exit1);
        result.add(exit2);
        return result;
    }
    
    
    public Server() {
        super();
    }

    /* (non-Javadoc)
     * @see com.mintplex.oeffioptimizer.IServer#addExit(com.mintplex.oeffioptimizer.model.Exit)
     */
    @Override
    public void addExit(Exit exit) {
        //do not transfer
    }
}
