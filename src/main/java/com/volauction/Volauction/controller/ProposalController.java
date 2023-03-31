package com.volauction.Volauction.controller;

import com.volauction.Volauction.domain.auction.Auction;
import com.volauction.Volauction.domain.auction.AuctionRepository;
import com.volauction.Volauction.domain.proposal.DTOProposal;
import com.volauction.Volauction.domain.proposal.DTOProposalCreate;
import com.volauction.Volauction.domain.proposal.Proposal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/proposal")
public class ProposalController {
    @Autowired
    private AuctionRepository auctionRepository;
    @PostMapping
    public ResponseEntity proposal(@Valid @RequestBody DTOProposalCreate data, UriComponentsBuilder uriBuilder){
        Auction auction = auctionRepository.getReferenceById(data.auctionId());
        Proposal proposal = new Proposal(data,auction);
        URI location = uriBuilder.path("/proposal/{id}").buildAndExpand(auction.getId()).toUri();
        return ResponseEntity.created(location).body(new DTOProposal(proposal));
    }
}
