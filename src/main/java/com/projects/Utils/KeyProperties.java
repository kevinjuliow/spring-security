package com.projects.Utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Data
public class KeyProperties {
    private RSAPublicKey publicKey ;
    private RSAPrivateKey privateKey ;

    public KeyProperties() {
        KeyPair keyPair  = KeyGeneratorUtility.keyGenerator();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }
}
