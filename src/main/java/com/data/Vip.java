package com.data;

public class Vip extends Person{

    private final String title;
    private final String bornDate;    //  to be formatted to fit it in LocalDate


    public Vip(String name, String title, String bornDate, String href){
        super(name, href);
        this.bornDate = bornDate;
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public String getBornDate() {
        return bornDate;
    }
}
