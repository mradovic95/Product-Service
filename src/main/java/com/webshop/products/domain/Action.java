package com.webshop.products.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Action extends BaseEntity {

    @Indexed(unique = true)
    private String title;
    @DBRef
    private List<Product> products = new ArrayList<>();
    private BigDecimal totalCostWithoutAction;
    private BigDecimal totalCostWithAction;

}
