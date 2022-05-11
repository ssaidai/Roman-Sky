package com.data;

import java.time.LocalDate;
import java.util.Set;

public class Person {
    private String name;
    private LocalDate bornDate;

    private Set<Person> parents, children, siblings;

    public Person(String name, LocalDate bornDate){
        this.name = name;
        this.bornDate = bornDate;
    }

    //  CHANGE THIS TO BOOLEAN
    public void addParent(Person parent){
        this.parents.add(parent);
    }

    public void addChild(Person parent){
        this.parents.add(parent);
    }

    public void addSibling(Person parent){
        this.parents.add(parent);
    }

    public String getName() {
        return name;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }
}
