package com.mintplex.oeffioptimizer.model;

import java.util.ArrayList;
import java.util.List;

public class Location {


    public Location(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int id;
    public String name;
    public int parent;
    
    public List<Location> children = new ArrayList<Location>();
    
    public void add(Location loc) {
        children.add(loc);
        loc.parent = id;
    }
    
    @Override
    public String toString() {
        return name;
    }
} 
