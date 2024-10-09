package io.github.yhugorocha.mapper;

import io.github.yhugorocha.dto.OpportunityDTO;
import io.github.yhugorocha.dto.ProposalDTO;
import io.github.yhugorocha.entity.OpportunityEntity;
import io.github.yhugorocha.entity.QuotationEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;

@ApplicationScoped
public class OpportunityMapper {

    public OpportunityEntity toEntity(ProposalDTO proposalDTO, QuotationEntity quotation){
        return OpportunityEntity.builder()
                .id(proposalDTO.proposalId())
                .customer(proposalDTO.customer())
                .priceTonner(proposalDTO.priceTonne())
                .date(LocalDateTime.now())
                .lastDollarQuotation(quotation.getCurrencyPrice()).build();
    }

    public OpportunityDTO toDTO(OpportunityEntity opportunityEntity){
        return OpportunityDTO.builder()
                .proposalId(opportunityEntity.getId())
                .customer(opportunityEntity.getCustomer())
                .priceTonne(opportunityEntity.getPriceTonner())
                .lastDollarQuotation(opportunityEntity.getLastDollarQuotation())
                .build();
    }
}
