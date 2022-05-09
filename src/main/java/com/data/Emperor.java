package com.data;

public class Emperor extends Person{
    private ChargeTime charge;
    private String deathCause;
    private String additionalInfo;

    //  TODO:   IMPLEMENT CLASS CONSTRUCTOR
    public Emperor(){
        super();    //  We need to pass the parameters to super constructor first
                    //  example usage should be: super("Noemi", "23-02-2004");

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
