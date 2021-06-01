package com.betlivefront.betlive;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

import java.util.List;

public class UserDeserializer extends JsonObjectDeserializer<User> {

    @Override
    protected User deserializeObject(JsonParser jsonParser,
                                     DeserializationContext deserializationContext,
                                     ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {


        int userId = nullSafeValue(jsonNode.get("userId"), Integer.class);
        String userName = nullSafeValue(jsonNode.get("userName"), String.class);
        BigDecimal balance = nullSafeValue(jsonNode.get("balance"), BigDecimal.class);



        return new User(userId,userName,balance);
    }
}
