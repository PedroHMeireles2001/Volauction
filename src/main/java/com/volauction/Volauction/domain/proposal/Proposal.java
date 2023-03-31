package com.volauction.Volauction.domain.proposal;

import com.volauction.Volauction.domain.auction.Auction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
public class Proposal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    BigDecimal price;

    @ManyToOne
    Auction auction;

    public Proposal(DTOProposalCreate data, Auction auction) {
        this.price = new BigDecimal(data.price());
        this.auction = auction;
    }
}
