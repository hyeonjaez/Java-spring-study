package com.example.shoppingmalljpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Product_Categories")
public class ProductCategory {

    @EmbeddedId
    @NotNull
    private Pk pk;

    @MapsId("categoryId")
    @ManyToOne
    @JoinColumn(name="CategoryID")
    private Category category;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name="ProductID")
    private Product product;


    @Embeddable
    @NoArgsConstructor
    @Setter
    @Getter
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @NotNull
        @Column(name = "ProductID")
        private Long productId;

        @NotNull
        @Column(name = "CategoryID")
        private Long categoryId;
    }
}
