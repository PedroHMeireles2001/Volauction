package com.volauction.Volauction.controller;

import com.volauction.Volauction.domain.auction.Auction;
import com.volauction.Volauction.domain.auction.AuctionRepository;
import com.volauction.Volauction.domain.auction.DTOAuction;
import com.volauction.Volauction.domain.proposal.DTOProposal;
import com.volauction.Volauction.domain.proposal.DTOProposalCreate;
import com.volauction.Volauction.domain.proposal.Proposal;
import com.volauction.Volauction.domain.proposal.ProposalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping("/proposal")
public class ProposalController {
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private ProposalRepository repository;
    @PostMapping
    public ResponseEntity proposal(@Valid @RequestBody DTOProposalCreate data, UriComponentsBuilder uriBuilder){
        Auction auction = auctionRepository.findById(data.auctionId()).get();
        Proposal proposal = new Proposal(data,auction);
        URI location = uriBuilder.path("/proposal/{id}").buildAndExpand(auction.getId()).toUri();

        auction.getProposals().add(proposal);
        auctionRepository.save(auction);
        return ResponseEntity.created(location).body(new DTOProposal(proposal));
    }
    @GetMapping("/{id}")
    public ResponseEntity getAuction(@PathVariable Long id){
        Proposal proposal = repository.getReferenceById(id);
        return ResponseEntity.ok(new DTOProposal(proposal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuction(@PathVariable Long id){
        Proposal proposal = repository.getReferenceById(id);
        repository.delete(proposal);
        return ResponseEntity.ok(new DTOProposal(proposal));
    }


}
