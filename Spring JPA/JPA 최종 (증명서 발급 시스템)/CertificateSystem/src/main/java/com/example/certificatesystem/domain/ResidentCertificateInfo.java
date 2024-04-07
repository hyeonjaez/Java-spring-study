package com.example.certificatesystem.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ResidentCertificateInfo {
    private Integer residentSerialNumber;
    private String name;
    private List<String> certificateTypeList;
}
