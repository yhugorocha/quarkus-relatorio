package io.github.yhugorocha.mapper;

import io.github.yhugorocha.dto.QuotationDTO;
import io.github.yhugorocha.entity.QuotationEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuotationMapper {

    public QuotationEntity toEntity(QuotationDTO quotationDTO){
        return QuotationEntity.builder().currencyPrice(quotationDTO.currencyPrice()).date(quotationDTO.date()).build();
    }
}
