package com.example.shoppingmalljpa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
@Table(name = "OrderDetails")
public class OrderDetail {

    @EmbeddedId
    private Pk pk;

    @Column(name = "ProductID")
    private Integer productId;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "UnitCost")
    private BigDecimal unitCost;

    @OneToOne
    private Order order;

    @Embeddable
    @NoArgsConstructor
    @Setter
    @Getter
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @NotNull
        @Column(name = "OrderID")
        private Integer orderId;

        @NotNull
        @Column(name = "user_id")
        private String userId;

    }
}
