package com.example.account.config;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;


@ConfigurationProperties(prefix = "com.example.account")
@Data
public class AccountAdaptorProperties {

    @NotNull
    private String address;
}
