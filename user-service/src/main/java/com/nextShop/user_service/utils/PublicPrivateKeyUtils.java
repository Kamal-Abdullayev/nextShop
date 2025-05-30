package com.nextShop.user_service.utils;

import com.nextShop.user_service.security.SecurityProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class PublicPrivateKeyUtils {

    private final SecurityProperties securityProperties;

    @Getter
    private static PrivateKey privateKey;
    @Getter
    private static PublicKey publicKey;

    public PublicPrivateKeyUtils(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        privateKey = preparePrivateKey();
        publicKey = preparePublicKey();
    }


    private PrivateKey preparePrivateKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(
                            securityProperties.getJwt().getPrivateKey()));
            return kf.generatePrivate(keySpecPKCS8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private PublicKey preparePublicKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(
                    Base64.getDecoder().decode(
                            securityProperties.getJwt().getPublicKey()));
            return kf.generatePublic(keySpecX509);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}