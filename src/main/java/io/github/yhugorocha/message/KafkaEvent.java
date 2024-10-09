package io.github.yhugorocha.message;

import io.github.yhugorocha.dto.ProposalDTO;
import io.github.yhugorocha.dto.QuotationDTO;
import io.github.yhugorocha.service.OpportunityService;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvent {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Inject
    OpportunityService opportunityService;

    @Incoming("proposal-in")
    public void receiveProposal(ProposalDTO proposal){
        LOG.info("-- Recebendo nova proposta --");
        opportunityService.buildOpportunity(proposal);
    }

    @Incoming("quotation-in")
    @Blocking
    public void receiveQuotation(QuotationDTO quotation){
        LOG.info("-- Recebendo nova quotation --");
        opportunityService.saveQuotation(quotation);
    }
}
