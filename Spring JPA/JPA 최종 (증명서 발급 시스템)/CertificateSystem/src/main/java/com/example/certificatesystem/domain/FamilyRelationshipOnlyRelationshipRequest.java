package com.example.certificatesystem.domain;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class FamilyRelationshipOnlyRelationshipRequest {
    @NotNull
    String relationShip;
}
