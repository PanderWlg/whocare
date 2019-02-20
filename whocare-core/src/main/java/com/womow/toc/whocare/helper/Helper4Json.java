package com.womow.toc.whocare.helper;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helper4Json {
    public static ObjectMapper jsonOm = new ObjectMapper();

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        jsonOm = objectMapper;
    }

}
