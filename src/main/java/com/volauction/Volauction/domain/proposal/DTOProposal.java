package com.volauction.Volauction.domain.proposal;

import com.volauction.Volauction.domain.auction.DTOAuction;

public record DTOProposal(String price,DTOAuction auction) {
    public DTOProposal(Proposal proposal) {
        this(proposal.getPrice().toString(),new DTOAuction(proposal.getAuction()));
    }
}
