package com.example.certificatesystem.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {

    @EmbeddedId
    @NotNull
    private Pk pk;

    @NotNull
    @Column(name = "house_movement_address")
    private String houseMovementAddress;

    @NotNull
    @Column(name = "last_address_yn")
    private String lastAddressYOrN;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @Embeddable
    @NoArgsConstructor
    @Setter
    @Getter
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Pk implements Serializable {

        @NotNull
        @Column(name = "household_serial_number")
        private Integer householdSerialNumber;

        @NotNull
        @Column(name = "house_movement_report_date")
        private String houseMovementReportDate;
    }
}
