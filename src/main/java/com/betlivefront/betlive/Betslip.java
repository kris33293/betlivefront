package com.betlivefront.betlive;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = BetslipDeserializer.class)
@SuppressWarnings("serial")
public class Betslip {

    private final int betslipId;
    private final List<Type> types;
    private final double totdalOdds;
    private final double totdalStake;
    private final double toWin;
    private final Ticket ticket;

    public Betslip(int betslipId, List<Type> types, double totdalOdds, double totdalStake, double toWin, Ticket ticket) {
        this.betslipId = betslipId;
        this.types = types;
        this.totdalOdds = totdalOdds;
        this.totdalStake = totdalStake;
        this.toWin = toWin;
        this.ticket = ticket;
    }

    public int getBetslipId() {
        return betslipId;
    }

    public List<Type> getTypes() {
        return types;
    }

    public double getTotdalOdds() {
        return totdalOdds;
    }

    public double getTotdalStake() {
        return totdalStake;
    }

    public double getToWin() {
        return toWin;
    }

    public Ticket getTicket() {
        return ticket;
    }


}
