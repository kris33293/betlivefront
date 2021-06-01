package com.betlivefront.betlive;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;
import java.util.List;

public class BetslipDeserializer extends JsonObjectDeserializer<Betslip> {

    @Override
    protected Betslip deserializeObject(JsonParser jsonParser,
                                    DeserializationContext deserializationContext,
                                    ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {


        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Type>> listType = new TypeReference<>() {
        };

        int betslipId = nullSafeValue(jsonNode.get("betslipId"), Integer.class);
        List<Type> types = mapper.readValue(jsonNode.get("types").textValue(),listType);
        double totalOdds = nullSafeValue(jsonNode.get("totalOdds"), Double.class);
        double totalStake = nullSafeValue(jsonNode.get("totalStake"), Double.class);
        double toWin = nullSafeValue(jsonNode.get("toWin"), Double.class);
        Ticket ticket = nullSafeValue(jsonNode.get("ticket"), Ticket.class);

        return new Betslip(betslipId,types,totalOdds,totalStake,toWin,ticket);
    }
}
