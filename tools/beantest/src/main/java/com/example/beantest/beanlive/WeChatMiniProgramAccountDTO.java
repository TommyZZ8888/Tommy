package com.example.beantest.beanlive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "wxminiapp")

public class WeChatMiniProgramAccountDTO {

    private String appId;

    private String appSecret;

    private String grantType;

    private String token;

    private String aesKey;
}
