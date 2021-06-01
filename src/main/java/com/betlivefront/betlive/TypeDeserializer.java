package com.betlivefront.betlive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TypeDeserializer extends JsonObjectDeserializer<Type> {

    @Override
    protected Type deserializeObject(JsonParser jsonParser,
                                       DeserializationContext deserializationContext,
                                       ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);



        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        int typeId = nullSafeValue(jsonNode.get("typeId"), Integer.class);
        String homeTeam = nullSafeValue(jsonNode.get("homeTeam"), String.class);
        String awayTeam = nullSafeValue(jsonNode.get("awayTeam"), String.class);
        String eventDate = nullSafeValue(jsonNode.get("eventDate"), String.class) ;
        double odd = nullSafeValue(jsonNode.get("odd"), Double.class);
        String yourType = nullSafeValue(jsonNode.get("yourType"), String.class) ;



        return new Type(typeId,homeTeam,awayTeam,eventDate,odd,yourType);
    }
}
