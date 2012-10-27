package com.mintplex.oeffioptimizer.model;

import java.util.ArrayList;
import java.util.List;

public class Location implements Comparable<Location> {

    public Location() {
    }
    
    public Location(String name) {
        this.name = name;
    }
    
    public Location(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public String key;
    public String name;
    public String parent = null;
    
    public String getParent() {
        return parent;
    }

    public List<Location> children = new ArrayList<Location>();
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public int compareTo(Location another) {
        if (another == null) return -1;
        if (name == null)  return 1;
        if (name == null && another.name == null) return 0;
        return name.compareTo(another.name);
    }
} 
