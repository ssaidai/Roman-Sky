package com.data;

import com.shapesecurity.salvation2.Values.Hash;

import java.time.LocalDate;
import java.util.*;

public class Person {
    private final String name;
    private final String href;    // used as ID to distinguish homonym persons

    private Set<String> parentsHref;
    private Set<String> marriedHref;
    private HashMap<String, Boolean> children = new HashMap<>();

    private final boolean vip;


    public Person(String name, String href, Set<String> parents, Set<String> married, HashMap<String, Boolean> children, boolean vip){
        this.name = name;
        this.href = href;
        this.parentsHref = parents;
        this.marriedHref = married;
        this.children = children;
        this.vip = vip;
    }

    public Set<String> getMarriedHrefs() {
        return marriedHref;
    }

    public Set<String> getParentsHrefs() {
        return parentsHref;
    }

    public String getHref() {
        return href;
    }

    public HashMap<String, Boolean> getChildren(){
        return this.children;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
