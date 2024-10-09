package io.github.yhugorocha.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Jacksonized
public record QuotationDTO(LocalDateTime date, BigDecimal currencyPrice) {
}
