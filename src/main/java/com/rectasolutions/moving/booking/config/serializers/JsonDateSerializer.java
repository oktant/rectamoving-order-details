package com.rectasolutions.moving.booking.config.serializers;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer <LocalDateTime>{

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        final String dateString = date.format(this.formatter);
        generator.writeString(dateString);
    }
}