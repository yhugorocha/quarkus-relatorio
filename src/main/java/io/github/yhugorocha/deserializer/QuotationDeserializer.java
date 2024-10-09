package io.github.yhugorocha.deserializer;

import io.github.yhugorocha.dto.QuotationDTO;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuotationDeserializer extends ObjectMapperDeserializer<QuotationDTO> {
    public QuotationDeserializer() {
        super(QuotationDTO.class);
    }
}

