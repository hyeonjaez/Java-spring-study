package com.example.shoppingmalljpa.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "UserAddress")
public class UserAddress {
    @EmbeddedId
    @NotNull
    private Pk pk;

    @NotNull
    @Column(name = "AddressName")
    private String addressName;

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class Pk implements Serializable {

        @NotNull
        @Column(name = "AddressID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long addressId;

        @NotNull
        @Column(name = "user_id")
        private String userId;
    }

}
