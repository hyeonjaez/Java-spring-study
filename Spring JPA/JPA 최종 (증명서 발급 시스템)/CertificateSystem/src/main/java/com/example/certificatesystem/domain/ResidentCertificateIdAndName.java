package com.example.certificatesystem.domain;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ResidentCertificateIdAndName {
    private Integer residentSerialNumber;
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResidentCertificateIdAndName that = (ResidentCertificateIdAndName) o;
        return Objects.equals(name, that.name) && Objects.equals(residentSerialNumber, that.residentSerialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, residentSerialNumber);
    }
}
