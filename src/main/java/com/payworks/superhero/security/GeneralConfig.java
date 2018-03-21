package com.payworks.superhero.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeneralConfig {

    @Value("${security.security-realm}")
    private String securityRealm;

    @Value("${security.jwt.client-id}")
    private String clientId;

    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    @Value("${security.jwt.scope-read}")
    private String scopeRead;

    @Value("${security.jwt.scope-write}")
    private String scopeWrite = "write";

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Value("${security.jwt.grant-type-refresh-token}")
    private String grantTypeRefreshToken;

    @Value("${security.jwt.grant-type-password}")
    private String grantTypePassword;

    @Value("${security.signing-key}")
    private String signingKey;

    public String getSecurityRealm() {
        return securityRealm;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getScopeRead() {
        return scopeRead;
    }

    public String getScopeWrite() {
        return scopeWrite;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public String getGrantTypeRefreshToken() {
        return grantTypeRefreshToken;
    }

    public String getGrantTypePassword() {
        return grantTypePassword;
    }

    public String getSigningKey() {
        return signingKey;
    }

    @Override
    public String toString() {
        return "GeneralConfig{" +
                "securityRealm='" + securityRealm + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientSecret=***" +
                ", scopeRead='" + scopeRead + '\'' +
                ", scopeWrite='" + scopeWrite + '\'' +
                ", resourceIds='" + resourceIds + '\'' +
                ", grantTypeRefreshToken='" + grantTypeRefreshToken + '\'' +
                ", grantTypePassword='" + grantTypePassword + '\'' +
                ", signingKey='" + signingKey + '\'' +
                '}';
    }
}
