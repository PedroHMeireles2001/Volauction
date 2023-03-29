package com.volauction.Volauction.domain.auction;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{
    String name;
    String description;
    String imageUrl;

    public Product(DTOProduct product) {
        this.imageUrl = product.imageUrl();
        this.description = product.description();
        this.name = product.name();
    }
}
