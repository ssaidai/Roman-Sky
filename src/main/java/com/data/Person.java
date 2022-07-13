package main.java.com.data;

import com.shapesecurity.salvation2.Values.Hash;

import java.time.LocalDate;
import java.util.*;

/**
 *  Class that identify every single entity
 */
public class Person {
    private final String name;
    private final String href;

    private final long articleID;   // used as ID to distinguish homonym persons

    private HashMap<String, Boolean> parents;
    private Set<String> marriedHref;
    private HashMap<String, Boolean> children;

    private final boolean vip;

    /**
     * Person constructor
     *
     * @param name
     * @param href
     * @param articleID
     * @param parents
     * @param married
     * @param children
     * @param vip
     */
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

    /**
     * get married entities' hrefs
     *
     * @return
     */
    public Set<String> getMarriedHrefs() {
        return marriedHref;
    }

    /**
     * get parents' hrefs
     *
     * @return
     */
    public HashMap<String, Boolean> getParents() {
        return parents;
    }

    /**
     * get Person his own href
     *
     * @return
     */
    public String getHref() {
        return href;
    }

    /**
     * get Person his own articleID
     *
     * @return
     */
    public long getArticleID() {
        return articleID;
    }

    /**
     * get Person Vip status
     *
     * @return
     */
    public boolean isVip() {
        return vip;
    }

    /**
     * get Children' hrefs
     *
     * @return
     */
    public HashMap<String, Boolean> getChildren(){
        return this.children;
    }


    /**
     * get Person his own name
     *
     * @return
     */
    @Override
    public String toString(){
        return this.name;
    }

}
