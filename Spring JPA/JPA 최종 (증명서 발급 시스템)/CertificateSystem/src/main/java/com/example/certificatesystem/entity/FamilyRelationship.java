package com.example.certificatesystem.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "family_relationship")
public class FamilyRelationship {

    @EmbeddedId
    @NotNull
    private Pk pk;

    @NotNull
    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_resident_serial_number")
    private Resident resident;

    @Embeddable
    @NoArgsConstructor
    @Setter
    @Getter
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @NotNull
        @Column(name = "base_resident_serial_number")
        private Integer baseResidentSerialNumber;

        @NotNull
        @Column(name = "family_resident_serial_number")
        private Integer familyResidentSerialNumber;
    }
}
