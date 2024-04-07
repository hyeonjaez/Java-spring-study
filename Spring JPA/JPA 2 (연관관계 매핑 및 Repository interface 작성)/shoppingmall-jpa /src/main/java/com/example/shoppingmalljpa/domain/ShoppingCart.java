package com.example.shoppingmalljpa.domain;

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
@Table(name = "ShoppingCart")
public class ShoppingCart {
    @EmbeddedId
    @NotNull
    private Pk pk;

    @NotNull
    private Integer amount;

    @MapsId("productId")
    @ManyToOne
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
        @Column(name = "user_id")
        private String userId;
    }
}
