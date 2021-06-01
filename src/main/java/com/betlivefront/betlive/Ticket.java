package com.betlivefront.betlive;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = TicketDeserializer.class)
@SuppressWarnings("serial")
public class Ticket {

    private final int ticketId;
    private final List<Type> types;
    private final double totdalOdds;
    private final double totdalStake;
    private final double toWin;
    private final String ticketStatus;
    private final Betslip betslip;

    public Ticket(int ticketId, List<Type> types, double totdalOdds, double totdalStake, double toWin, String ticketStatus, Betslip betslip) {
        this.ticketId = ticketId;
        this.types = types;
        this.totdalOdds = totdalOdds;
        this.totdalStake = totdalStake;
        this.toWin = toWin;
        this.ticketStatus = ticketStatus;
        this.betslip = betslip;
    }

    public int getTicketId() {
        return ticketId;
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

    public String getTicketStatus() {
        return ticketStatus;
    }

    public Betslip getBetslip() {
        return betslip;
    }
}
