package com.volauction.Volauction.domain.auction;

import com.volauction.Volauction.domain.proposal.DTOProposalPrice;

import java.util.List;
import java.util.stream.Collectors;

public record DTOAuctionProposal(Long id, String initialPrice, DTOProduct product, List<DTOProposalPrice> proposals) {
    public DTOAuctionProposal(Auction auction){
        this(auction.getId(),
                auction.getInitialPrice().toString(),
                new DTOProduct(auction.getProduct()),
                auction.getProposals().stream().map(DTOProposalPrice::new).collect(Collectors.toList())
        );
    }
}
