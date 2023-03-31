package com.volauction.Volauction.domain.auction;

import com.volauction.Volauction.domain.proposal.Proposal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal initialPrice;
    @Embedded
    private Product product;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Proposal> proposals;
    public Auction(DTOAuctionCreate data) {
        this.product = new Product(data.product());
        this.initialPrice = new BigDecimal(data.initialPrice());
    }

    public void edit(DTOAuctionEdit data) {
        this.product.imageUrl = data.imageUrl();
    }
}
