package com.volauction.Volauction.controller;

import com.volauction.Volauction.domain.auction.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    private AuctionRepository repository;
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody DTOAuctionCreate data, UriComponentsBuilder uriBuilder){
        Auction auction = new Auction(data);
        repository.save(auction);
        URI location = uriBuilder.path("/auction/{id}").buildAndExpand(auction.getId()).toUri();
        return ResponseEntity.created(location).body(new DTOAuction(auction));
    }
    @GetMapping
    public ResponseEntity<Page<DTOAuctionProposal>> pageable(@PageableDefault Pageable pageable){
        Page<Auction> result = repository.findAll(pageable);
        Page<DTOAuctionProposal> page = result.map(DTOAuctionProposal::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAuction(@PathVariable Long id){
        Auction auction = repository.getReferenceById(id);
        return ResponseEntity.ok(new DTOAuctionProposal(auction));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuction(@PathVariable Long id){
        Auction auction = repository.getReferenceById(id);
        repository.delete(auction);
        return ResponseEntity.ok(new DTOAuction(auction));
    }

    @PutMapping("/{id}")
    public ResponseEntity editAuction(@PathVariable Long id,@Valid @RequestBody DTOAuctionEdit data){
        Optional<Auction> result = repository.findById(id);
        if(result.isEmpty())
            return ResponseEntity.notFound().build();

        Auction auction = result.get();
        auction.edit(data);
        repository.save(auction);

        return ResponseEntity.ok(new DTOAuction(auction));
    }


}
