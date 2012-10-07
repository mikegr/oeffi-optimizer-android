package com.mintplex.oeffioptimizer.model;

public class Exit {

    
    public Exit(int parent, String name, String hint) {
        this.location = parent;
        this.name = name;
        this.hint = hint;
        
    }
    public Exit(int parent, int id, String name, String hint) {
        this.location = parent;
        this.id = id;
        this.name = name;
        this.hint = hint;
    }
    
    public int location;
    public int id;
    public String name;
    public String hint;
}
