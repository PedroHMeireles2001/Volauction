package com.volauction.Volauction.domain.auction;

public record DTOAuction(Long id, String initialPrice, DTOProduct product) {
    public DTOAuction(Auction auction){
        this(auction.getId(), auction.getInitialPrice().toString(), new DTOProduct(auction.getProduct()));
    }
}
