package com.l2i_e_commerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements MeiliSearchModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imageUrl;
    
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private BigDecimal regularPrice;
    
    private boolean inStock;
    
    private boolean isNewCollection;
    
    private String language;
    
    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Sale sale;
    
    private int totalSales;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @Override
    public String getMeiliSearchId() {
        return String.valueOf(getId());
    }
    
    @Override
    public void setMeiliSearchId(String meiliSearchId) {
        this.id = Long.parseLong(meiliSearchId);
    }
    
    public Item(String imageUrl, String description, BigDecimal regularPrice, boolean inStock, boolean isNewCollection, String language, int totalSales) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.regularPrice = regularPrice;
        this.inStock = inStock;    	
        this.isNewCollection = isNewCollection;
        this.language = language;
        this.totalSales = totalSales;

    }
}

