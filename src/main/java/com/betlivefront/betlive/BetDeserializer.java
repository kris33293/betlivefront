package com.betlivefront.betlive;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;
import java.util.Date;

public class BetDeserializer extends JsonObjectDeserializer<Bet> {

    @Override
    protected Bet deserializeObject(JsonParser jsonParser,
                                       DeserializationContext deserializationContext,
                                       ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {



        int betId = nullSafeValue(jsonNode.get("betId"), Integer.class);
        double oddHome = nullSafeValue(jsonNode.get("oddHome"), Double.class);
        double oddAway = nullSafeValue(jsonNode.get("oddAway"), Double.class);
        double oddDraw = nullSafeValue(jsonNode.get("oddDraw"), Double.class);
        String homeTeam = nullSafeValue(jsonNode.get("homeTeam"), String.class);
        String awayTeam = nullSafeValue(jsonNode.get("awayTeam"), String.class);
        String eventDate = nullSafeValue(jsonNode.get("eventDate"), String.class);

        return new Bet(betId,oddHome,oddAway,oddDraw,homeTeam,awayTeam,eventDate);
    }
}
