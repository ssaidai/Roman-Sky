package com.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Person {
    private final String name;
    private String href = null;    // used as ID to distinguish homonym persons

    private Set<Person> parents = new HashSet<>();
    private Set<Person>married = new HashSet<>();
    private HashMap<Person, Boolean> children = new HashMap<>();


    public Person(String name, String href){
        this.name = name;
        this.href = href;
    }

    public void addParent(Person parent){
        this.parents.add(parent);
    }

    public void addMarried(Person married){
        this.married.add(married);
    }

    public void addChild(Person child, boolean adopted){

        this.children.put(child, adopted);
    }


    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    public ArrayList<Person> getParents() {
        return new ArrayList<>(parents);
    }

    public ArrayList<Person> getMarried(){
        return new ArrayList<>(married);
    }

    public HashMap<Person, Boolean> getChildren(){
        return this.children;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
