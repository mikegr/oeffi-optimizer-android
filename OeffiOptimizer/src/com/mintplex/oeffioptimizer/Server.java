package com.mintplex.oeffioptimizer;

import com.mintplex.oeffioptimizer.model.Exit;
import com.mintplex.oeffioptimizer.model.Location;

import java.util.ArrayList;
import java.util.List;

public class Server {

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
    
    public void addLocation(Location loc) {
        // do not transfer
    }
    
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

    public void addExit(Exit exit) {
        //do not transfer
    }
}
