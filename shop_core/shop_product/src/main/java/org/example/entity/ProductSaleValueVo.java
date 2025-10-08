package org.example.entity;

import lombok.Data;

@Data
public class ProductSaleValueVo {
    private Long productId;

    private String salePropertyKeyName;

    private String salePropertyValueName;
}
