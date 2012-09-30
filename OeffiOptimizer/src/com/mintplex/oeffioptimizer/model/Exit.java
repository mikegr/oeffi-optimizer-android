package com.mintplex.oeffioptimizer.model;

public class Exit {

    
    public Exit(int parent, int id, String name, String hint) {
        super();
        this.parent = parent;
        this.id = id;
        this.name = name;
        this.hint = hint;
    }
    
    public int parent;
    public int id;
    public String name;
    public String hint;
}
