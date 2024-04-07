package com.example.certificatesystem.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentRequest {

    @NotNull
    Integer residentSerialNumber;

    @NotNull
    String name;

    @NotNull
    String residentRegistrationNumber;

    @NotNull
    String genderCode;

    @NotNull
    String birthDate;

    @NotNull
    String birthPlaceCode;

    String registrationBaseAddress;

    String deathDate;

    String deathPlaceCode;

    String deathPlaceAddress;
}
