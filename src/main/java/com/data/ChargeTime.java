package com.data;

import java.time.LocalDate;
import java.time.Period;

public class ChargeTime {
    private LocalDate start, end;

    //  TODO:   IMPLEMENT CLASS CONSTRUCTOR


    public String getTotalCharge(){
        Period totalCharge = Period.between(start, end);
        return totalCharge.getYears() + "years";
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
