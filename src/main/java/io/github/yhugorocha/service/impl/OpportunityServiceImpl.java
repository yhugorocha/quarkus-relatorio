package io.github.yhugorocha.service.impl;

import io.github.yhugorocha.dto.OpportunityDTO;
import io.github.yhugorocha.dto.ProposalDTO;
import io.github.yhugorocha.dto.QuotationDTO;
import io.github.yhugorocha.entity.QuotationEntity;
import io.github.yhugorocha.mapper.OpportunityMapper;
import io.github.yhugorocha.mapper.QuotationMapper;
import io.github.yhugorocha.repository.OpportunityRepository;
import io.github.yhugorocha.repository.QuotationRepository;
import io.github.yhugorocha.service.OpportunityService;
import io.github.yhugorocha.utils.XLSXHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class OpportunityServiceImpl implements OpportunityService {

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    OpportunityRepository opportunityRepository;

    @Inject
    QuotationMapper quotationMapper;

    @Inject
    OpportunityMapper opportunityMapper;

    @Inject
    XLSXHelper xlsxHelper;

    @Transactional
    @Override
    public void buildOpportunity(ProposalDTO proposal) {
        var quotation = quotationRepository.findAll().stream().max(Comparator.comparing(QuotationEntity::getCurrencyPrice));
        if(quotation.isEmpty()) {
            throw new RuntimeException("Não há nenhuma quotation.");
        }
        opportunityRepository.persist(opportunityMapper.toEntity(proposal, quotation.get()));

    }

    @Transactional
    @Override
    public void saveQuotation(QuotationDTO quotation) {
        quotationRepository.persist(quotationMapper.toEntity(quotation));
    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return opportunityRepository.findAll().stream().map(opportunityMapper::toDTO).toList();
    }

    @Override
    public ByteArrayOutputStream generateXLSXOpportunityReport() {
        String [] head = {"ID", "CUSTOMER", "PRICE_TONNE", "LAST_DOLLAR_QUOTATION"};
        return xlsxHelper.exportToXLSX(this.generateOpportunityData(), head);
    }
}
