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
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {

    @EmbeddedId
    @NotNull
    private Pk pk;

    @NotNull
    @Column(name = "report_date")
    LocalDate reportDate;

    @NotNull
    @Column(name = "household_relationship_code")
    String householdRelationshipCode;

    @NotNull
    @Column(name = "household_composition_change_reason_code")
    String householdCompositionChangeReasonCode;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

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
        @Column(name = "resident_serial_number")
        private Integer residentSerialNumber;
    }
}
