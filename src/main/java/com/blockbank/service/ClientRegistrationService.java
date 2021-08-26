

package com.blockbank.service;


/**@author Karish Resodikromo
 * Deze serviceklasse heeft methode register om een klant te registreren
 * */


import com.blockbank.database.domain.Account;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientRegistrationService {


    private HashService hashservice;
    private RootRepository rootrepository;
    private PasswordValidationService passwordValidationService;
    private SaltGenerator saltGenerator;
    private IbanGenerator ibanGenerator;

    @Autowired
    public ClientRegistrationService(HashService hashservice, RootRepository rootrepository,
                                     PasswordValidationService passwordValidationService, SaltGenerator saltGenerator,
                                     IbanGenerator ibanGenerator) {
        this.hashservice = hashservice;
        this.rootrepository = rootrepository;
        this.passwordValidationService = passwordValidationService;
        this.saltGenerator = saltGenerator;
        this.ibanGenerator = ibanGenerator;
    }

    //TODO METHODE AFMAKEN UsernameValidationService.isValid
    public boolean register(UserDetails userDetails) {
        // check isValid username en password
        if (passwordValidationService.isValid(userDetails.getPassword())) {
            String salt = saltGenerator.generateSalt();
            String hashedPassword = hashservice.ultimateHash(userDetails.getPassword(), salt);
            // userdetails bijwerken
            userDetails.setSalt(salt);
            userDetails.setPassword(hashedPassword);
            // userdetails opslaan
            rootrepository.saveUserDetails(userDetails);
            // account aanmaken
            Account newAccount = new Account(userDetails.getUserID());
            // new iban
            String iban = ibanGenerator.generateIban();
            // plaatsen in nieuw account
            newAccount.setIban(iban);
            // nieuw account opslaan
            rootrepository.saveAccount(newAccount);
            return true;
        } else {
            return false;
        }
    }
}

