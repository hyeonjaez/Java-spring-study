package com.example.certificatesystem.domain;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationshipRequest {

    @NotNull
    Integer familySerialNumber;

    @NotNull
    String relationship;
}
