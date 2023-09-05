package com.projects.Utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtility {


    public static KeyPair keyGenerator (){
        KeyPair keyPair;
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA"); //JWT Algorithms used
            keyPairGenerator.initialize(2048); //2048 bits
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception err){
            throw new IllegalStateException();
        }
        return keyPair;
    }
}
