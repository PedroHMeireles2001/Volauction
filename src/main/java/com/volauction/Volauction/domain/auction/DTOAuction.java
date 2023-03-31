package com.volauction.Volauction.domain.auction;

import com.volauction.Volauction.domain.proposal.DTOProposal;

import java.util.List;
import java.util.stream.Collectors;

public record DTOAuction(Long id, String initialPrice, DTOProduct product) {
    public DTOAuction(Auction auction){
        this(auction.getId(),
                auction.getInitialPrice().toString(),
                new DTOProduct(auction.getProduct()));
    }
}
