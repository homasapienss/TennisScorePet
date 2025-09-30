package edu.tennis.score.homasapienss.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static <T> String writeJSON(T payload) throws JsonProcessingException {
        return objectMapper.writeValueAsString(payload);
    }
}
