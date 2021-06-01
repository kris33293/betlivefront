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

public class TicketDeserializer extends JsonObjectDeserializer<Ticket> {

    @Override
    protected Ticket deserializeObject(JsonParser jsonParser,
                                        DeserializationContext deserializationContext,
                                        ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Type>> listType = new TypeReference<>() {
        };


        int ticketId = nullSafeValue(jsonNode.get("ticketId"), Integer.class);
        List<Type> types = mapper.readValue(jsonNode.get("types").textValue(),listType);
        double totdalOdds = nullSafeValue(jsonNode.get("totalOdds"), Double.class);
        double totdalStake = nullSafeValue(jsonNode.get("totalStake"), Double.class);
        double toWin = nullSafeValue(jsonNode.get("toWin"), Double.class);
        String ticketStatus = nullSafeValue(jsonNode.get("ticketStatus"), String.class);
        Betslip betslip = nullSafeValue(jsonNode.get("betslip"), Betslip.class);
        return new Ticket(ticketId,types,totdalOdds,totdalStake,toWin,ticketStatus,betslip);
    }
}
