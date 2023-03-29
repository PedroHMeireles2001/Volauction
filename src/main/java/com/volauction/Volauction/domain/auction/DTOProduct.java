package com.volauction.Volauction.domain.auction;

public record DTOProduct(String name, String description, String imageUrl) {
    public DTOProduct(Product product) {
        this(product.getName(), product.getDescription(), product.getImageUrl());
    }
}
