package io.github.yhugorocha.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opportunity")
public class OpportunityEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime date;

    @Column(name = "proposal_id")
    private Long  proposalId;

    private String customer;

    @Column(name = "price_tonne", columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal priceTonner;

    @Column(name = "last_currency_quotation", columnDefinition = "DECIMAL(10, 4)")
    private BigDecimal lastDollarQuotation;
}
