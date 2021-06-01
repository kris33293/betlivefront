package com.betlivefront.betlive;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@JsonDeserialize(using = TypeDeserializer.class)
@SuppressWarnings("serial")
public class Type {


    private final int typeId;
    private final String homeTeam;
    private final String awayTeam;
    private final String eventDate;
    private final double odd;
    private final String yorType;

    public Type(int typeId, String homeTeam, String awayTeam, String eventDate, double odd, String yotuType) {
        this.typeId = typeId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.eventDate = eventDate;
        this.odd = odd;
        this.yorType = yotuType;

    }

    public int getTypeId() {
        return typeId;
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

    public double getOdd() {
        return odd;
    }


    public String getYorType() {
        return yorType;
    }
}
