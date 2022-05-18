package com.data;

import java.time.LocalDate;
import java.time.Period;

public class ChargeTime {
    private LocalDate start, end;

    //  TODO:   IMPLEMENT CLASS CONSTRUCTOR


    public int getTotalCharge(){
        return Period.between(start , end).getYears();
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
