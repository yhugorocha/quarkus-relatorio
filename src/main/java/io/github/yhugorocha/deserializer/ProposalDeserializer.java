package io.github.yhugorocha.deserializer;

import io.github.yhugorocha.dto.ProposalDTO;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class ProposalDeserializer extends ObjectMapperDeserializer<ProposalDTO> {
    public ProposalDeserializer() {
        super(ProposalDTO.class);
    }
}

