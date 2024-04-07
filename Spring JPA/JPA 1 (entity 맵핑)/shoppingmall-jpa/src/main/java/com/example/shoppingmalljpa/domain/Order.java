package com.example.shoppingmalljpa.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "Orders")
public class Order {
    @NotNull
    @EmbeddedId
    private Pk pk;

    @Column(name = "OrderDate")
    private LocalDateTime orderDate;
    @Column(name = "ShipDate")
    private LocalDateTime shipDate;

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
        private String userID;
    }

}
