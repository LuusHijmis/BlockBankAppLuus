

package com.blockbank.service;


/**@author Karish Resodikromo
 * Deze serviceklasse heeft methode register om een klant te registreren
 * */


import com.blockbank.database.domain.*;
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
    public UserDetails register(UserDTO userDTO) {
        // check isValid username en password
        if (passwordValidationService.isValid(userDTO.getPassword())) {
            String salt = saltGenerator.generateSalt();
            String hashedPassword = hashservice.ultimateHash(userDTO.getPassword(), salt);
            //Maak objecten voor User
            ClientDetails clientDetails = new ClientDetails(userDTO.getFirstname(),userDTO.getPrefix(),
                    userDTO.getLastname(),userDTO.getDateOfBirth(), userDTO.getBsn(), userDTO.getEmailAddress());
            Address address = new Address(userDTO.getAddress(), userDTO.getHouseNumber(), userDTO.getAffix(),
                    userDTO.getPostalCode(), userDTO.getCity(), userDTO.getCountry());
            UserDetails userDetails = new UserDetails(userDTO.getUsername(), hashedPassword, salt, clientDetails, address);
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
            return userDetails;
        } else {
            return null;
        }
    }
}

