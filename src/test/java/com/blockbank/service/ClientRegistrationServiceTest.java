//package com.blockbank.service;
//
//import com.blockbank.database.domain.Address;
//import com.blockbank.database.domain.ClientDetails;
//import com.blockbank.database.domain.RegistrationDTO;
//import com.blockbank.database.domain.UserDetails;
//import com.blockbank.database.repository.RootRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.mockito.Mockito;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest
//@ActiveProfiles("test")
//class ClientRegistrationServiceTest {
//
//    private RootRepository mockRepo;
//    private HashService mockHash = new HashService();
//    private PasswordValidationService mockPass = new PasswordValidationService();
//    private SaltGenerator mockSalt = new SaltGenerator();
//    private IbanGenerator mockIban = new IbanGenerator();
//    private RootRepository rootRepository;
//    private UsernameValidationService mockUsername = new UsernameValidationService(rootRepository);
//    private ClientRegistrationService clientRegistrationService;
//
////    @Autowired
////    public ClientRegistrationServiceTest (RootRepository rootRepository) {
////        this.rootRepository = rootRepository;
////    }
//
//    @BeforeAll
//    public void setUP() {
//        mockRepo = Mockito.mock(RootRepository.class);
//        clientRegistrationService = new ClientRegistrationService(mockHash, rootRepository, mockPass, mockSalt, mockIban, mockUsername);
//    }
//
//    @Test
//    public void memberServiceNotNull() {
//        assertThat(clientRegistrationService).isNotNull();
//    }
//
//    RegistrationDTO registrationDTO = new RegistrationDTO("Hannah", "van", "Dam",
//            LocalDate.parse("1999-01-07"), 123477759, "hannah@hva.nl", "hannah", "345",
//            "Prinsessenstraat", 29, "", "1300PT", "Rotterdam",
//            "Nederland");
//
//    @Test
//    public void registerTest() {
//        UserDetails result = clientRegistrationService.register(registrationDTO);
//        System.out.println(result.getUsername());
//        System.out.println(result.getPassword());
//        System.out.println(result.getSalt());
//        System.out.println(result);
//        Address address = new Address(registrationDTO.getAddress(), registrationDTO.getHouseNumber(), registrationDTO.getAffix(),
//                registrationDTO.getPostalCode(), registrationDTO.getCity(), registrationDTO.getCountry());
//        ClientDetails clientDetails = new ClientDetails(registrationDTO.getFirstname(), registrationDTO.getPrefix(),
//                registrationDTO.getLastname(), registrationDTO.getDateOfBirth(), registrationDTO.getBsn(), registrationDTO.getEmailAddress());
//        UserDetails userDetails = new UserDetails(registrationDTO.getUsername(), registrationDTO.getPassword(), "12345",
//                clientDetails, address);
//        assertThat(clientRegistrationService.register(registrationDTO).equals(userDetails));
//    }
//
//}