package com.data;

import com.shapesecurity.salvation2.Values.Hash;

import java.time.LocalDate;
import java.util.*;

public class Person {
    private final String name;
    private final String href;

    private final long articleID;   // used as ID to distinguish homonym persons

    private HashMap<String, Boolean> parents;
    private Set<String> marriedHref;
    private HashMap<String, Boolean> children;

    private final boolean vip;


    public Person(String name, String href, long articleID, HashMap<String, Boolean> parents, Set<String> married, HashMap<String, Boolean> children, boolean vip){
        this.name = name;
        this.href = href;
        this.articleID = articleID;
        this.parents = parents;
        this.marriedHref = married;
        this.children = children;
        this.vip = vip;
        if(this.parents == null){
            this.parents = new HashMap<>();
        }
        if(this.marriedHref == null){
            this.marriedHref = new HashSet<>();
        }
        if(this.children == null){
            this.children = new HashMap<>();
        }
    }

    public Set<String> getMarriedHrefs() {
        return marriedHref;
    }

    public HashMap<String, Boolean> getParents() {
        return parents;
    }

    public String getHref() {
        return href;
    }

    public long getArticleID() {
        return articleID;
    }

    public String getName() {
        return name;
    }

    public boolean isVip() {
        return vip;
    }

    public HashMap<String, Boolean> getChildren(){
        return this.children;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
