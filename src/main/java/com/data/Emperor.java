package com.data;

import java.time.LocalDate;

public class Emperor extends Person{
    private ChargeTime charge;
    private String deathCause;
    private String additionalInfo;
    private String Title;

    //  TODO:   IMPLEMENT CLASS CONSTRUCTOR
    public Emperor(String name, LocalDate bornDate, String href){
        super(name, bornDate, href);    //  We need to pass the parameters to super constructor first
                                  //  example usage should be: super("Noemi", "23-02-2004")
        //  Constructor implementation here

    }

    public ChargeTime getCharge() {
        return charge;
    }

    public String getDeathCause() {
        return deathCause;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
