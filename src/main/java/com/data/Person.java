package com.data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public class Person {
    private final String name;

    private String href = null;    // used as ID to distinguish homonym persons

    private Set<Person> parents, married;

    private HashMap<Person, Boolean> children;

    public Person(String name, String href){
        this.name = name;
        this.href = href;
    }

    public void addParent(Person parent){
        this.parents.add(parent);
    }

    public void addChild(Person child, boolean adopted){

        this.children.put(child, adopted);
    }

    public void addMarried(Person married){
        this.married.add(married);
    }

    public String getName() {
        return name;
    }

    public HashMap<Person, Boolean> getChildren(){
        return this.children;
    }

    public Set<Person> getMarried(){
        return this.married;
    }
}
