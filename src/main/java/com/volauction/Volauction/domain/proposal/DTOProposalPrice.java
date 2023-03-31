package com.volauction.Volauction.domain.proposal;

import com.volauction.Volauction.domain.auction.DTOAuction;

public record DTOProposalPrice(String price) {
    public DTOProposalPrice(Proposal proposal) {
        this(proposal.getPrice().toString());
    }
}
