package com.betlivefront.betlive;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Date;

@JsonDeserialize(using = BetDeserializer.class)
@SuppressWarnings("serial")
public class Bet implements Serializable {

    private final int betId;
    private final double oddHome;
    private final double oddAway;
    private final double oddDraw;
    private final String homeTeam;
    private final String awayTeam;
    private final String eventDate;

    public Bet(int betId, double oddHome, double oddAway, double oddDraw, String homeTeam, String awayTeam, String eventDate) {
        this.betId = betId;
        this.oddHome = oddHome;
        this.oddAway = oddAway;
        this.oddDraw = oddDraw;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.eventDate = eventDate;
    }

    public int getBetId() {
        return betId;
    }

    public double getOddHome() {
        return oddHome;
    }

    public double getOddAway() {
        return oddAway;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getEventDate() {
        return eventDate;
    }

    public double getOddDraw() {
        return oddDraw;
    }
}
