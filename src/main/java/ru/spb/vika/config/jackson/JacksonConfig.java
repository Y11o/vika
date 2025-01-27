package ru.spb.vika.config.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        JsonFactory jsonFactory = new JsonFactory();

        StreamReadConstraints constraints = StreamReadConstraints.builder()
                .maxStringLength(40000000)
                .build();

        jsonFactory.setStreamReadConstraints(constraints);

        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }
}
