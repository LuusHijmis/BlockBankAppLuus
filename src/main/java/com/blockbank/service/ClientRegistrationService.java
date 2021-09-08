

package com.blockbank.service;


/**@author Karish Resodikromo
 * Deze serviceklasse heeft methode register om een klant te registreren
 * */



import com.blockbank.database.domain.*;
import com.blockbank.database.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientRegistrationService {

    private final Logger logger = LoggerFactory.getLogger(ClientRegistrationService.class);


    private HashService hashservice;
    private RootRepository rootrepository;
    private PasswordValidationService passwordValidationService;
    private SaltGenerator saltGenerator;
    private IbanGenerator ibanGenerator;
    private UsernameValidationService usernameValidationService;

    @Autowired
    public ClientRegistrationService(HashService hashservice, RootRepository rootrepository,
                                     PasswordValidationService passwordValidationService, SaltGenerator saltGenerator,
                                     IbanGenerator ibanGenerator, UsernameValidationService usernameValidationService) {
        super();
        this.hashservice = hashservice;
        this.rootrepository = rootrepository;
        this.passwordValidationService = passwordValidationService;
        this.saltGenerator = saltGenerator;
        this.ibanGenerator = ibanGenerator;
        this.usernameValidationService = usernameValidationService;
        logger.info("New ClientRegistrationService");
    }

    //TODO METHODE AFMAKEN met return
    public UserDetails register(RegistrationDTO registrationDTO) {
        if (passwordValidationService.isValid(registrationDTO.getPassword())
                && usernameValidationService.isValid(registrationDTO.getUsername())) {
            String salt = saltGenerator.generateSalt();
            String hashedPassword = hashservice.ultimateHash(registrationDTO.getPassword(), salt);
            //Maak objecten voor User
            ClientDetails clientDetails = new ClientDetails(registrationDTO.getFirstname(),registrationDTO.getPrefix(),
                    registrationDTO.getLastname(),registrationDTO.getDateOfBirth(), registrationDTO.getBsn(), registrationDTO.getEmailAddress());
            Address address = new Address(registrationDTO.getAddress(), registrationDTO.getHouseNumber(), registrationDTO.getAffix(),
                    registrationDTO.getPostalCode(), registrationDTO.getCity(), registrationDTO.getCountry());
            UserDetails userDetails = new UserDetails(registrationDTO.getUsername(), hashedPassword, salt, clientDetails, address);
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

