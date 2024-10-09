package io.github.yhugorocha.service;

import io.github.yhugorocha.dto.OpportunityDTO;
import io.github.yhugorocha.dto.ProposalDTO;
import io.github.yhugorocha.dto.QuotationDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayOutputStream;
import java.util.List;

@ApplicationScoped
public interface OpportunityService {

    void buildOpportunity(ProposalDTO proposal);

    void saveQuotation(QuotationDTO quotation);

    List<OpportunityDTO> generateOpportunityData();

    ByteArrayOutputStream generateXLSXOpportunityReport();
}
