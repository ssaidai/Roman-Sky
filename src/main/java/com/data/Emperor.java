package com.data;

public class Emperor extends Vip{

    private String deathCause;
    private ChargeTime charge;
    private String additionalInfo;


    public Emperor(String name, String title, String bornDate, String href) {
        super(name, title, bornDate, href);
    }


    public String getDeathCause() {
        return deathCause;
    }

    public ChargeTime getCharge() {
        return charge;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

}
