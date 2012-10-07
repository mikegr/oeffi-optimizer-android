package com.mintplex.oeffioptimizer.model;

public class Exit {

    
    public Exit(String parent, String name, String hint) {
        this.location = parent;
        this.name = name;
        this.hint = hint;
        
    }
    public Exit(String parent, String id, String name, String hint) {
        this.location = parent;
        this.id = id;
        this.name = name;
        this.hint = hint;
    }
    
    public String location;
    public String id;
    public String name;
    public String hint;
}
