/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sft.accreditation.security;

import com.jmoordbcoreencripter.jmoordbencripter.Encryptor;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import jakarta.security.enterprise.identitystore.IdentityStore;
import static java.util.Arrays.asList;
import java.util.HashSet;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
public class AuthentificactionIdentityStore implements IdentityStore {
   

// <editor-fold defaultstate="collapsed" fields ">
    String userAutentification;
    String passwordAutentification;
    String secretKey="SCox1jmWrkma$*opne2Amwz";
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Inject">
    
//     @Inject
//           LoggerServices loggerServices;
// </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="Microprofile Config">
    @Inject
    private Config config;
    //otp
    @Inject
    @ConfigProperty(name = "userSecurity", defaultValue = "")
    private String userSecurity;
    @Inject
    @ConfigProperty(name = "passwordSecurity", defaultValue = "")
    private String passwordSecurity;

//    //#--Path Images
//    @Inject
//    @ConfigProperty(name = "pathBaseLinuxAddUserHomeLogger", defaultValue = "true")
//    private Boolean pathBaseLinuxAddUserHomeLogger;
//
//    @Inject
//    @ConfigProperty(name = "pathLinuxLogger", defaultValue = " ")
//    private String pathLinuxLogger;
//    @Inject
//    @ConfigProperty(name = "pathWindowsLogger", defaultValue = " ")
//    private String pathWindowsLogger;
 // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="init">
    @PostConstruct
    public void init() {
        try {
            

        } catch (Exception e) {
//            loggerServices.processException(JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e, true); 
        }

    }

   
    // </editor-fold> 
     // <editor-fold defaultstate="collapsed" desc="CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential)">
    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        try {
      //String encryptedString = Encryptor.encrypt(password, secretKey);
      System.out.println("Encriptar  usernamePasswordCredential.getCaller() "+ usernamePasswordCredential.getCaller());       
            System.out.println("Encriptar  usernamePasswordCredential.getPassword( "+ usernamePasswordCredential.getPasswordAsString());       
                  String userEncryptedString = Encryptor.encrypt(usernamePasswordCredential.getCaller(), secretKey);
                  String passwordEncryptedString = Encryptor.encrypt(usernamePasswordCredential.getPasswordAsString(), secretKey);
            System.out.println("userEncryptedString "+userEncryptedString);
            System.out.println("passwordEncryptedString "+passwordEncryptedString);
             // String decryptedString = Encryptor.decrypt(jWTEntity.getPassword(), secretKey);
    
            userAutentification = Encryptor.decrypt(userSecurity, secretKey);
            passwordAutentification = Encryptor.decrypt(passwordSecurity,secretKey);

            if (usernamePasswordCredential.compareTo(userAutentification, passwordAutentification)) {
                System.out.println("--> Coinciden las credenciales");
                return new CredentialValidationResult(userAutentification, new HashSet<>(asList("admin", "testing")));
            }else{
                System.out.println("No coinciden las credenciuales");
            }
        } catch (Exception e) {
//            loggerServices.processException(JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e, true); 
             System.out.println("validate() "+e.getLocalizedMessage());
           
        }

        return INVALID_RESULT;
    }
    // </editor-fold>
}
