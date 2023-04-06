package com.l2i_e_commerce.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;
    
    @OneToMany(mappedBy = "category")
    private List<Book> books;

    public Category(String name, String description, Category parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
    }
}
