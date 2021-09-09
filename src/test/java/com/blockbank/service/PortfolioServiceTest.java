package com.blockbank.service;

import com.blockbank.database.domain.*;
import com.blockbank.database.repository.RootRepository;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.*;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PortfolioServiceTest {

    private PortfolioService portfolioServiceUnderTest;
    private RootRepository mockRepo;


    private final Logger logger = LoggerFactory.getLogger(ExchangeRateUpdateServiceTest.class);

    @BeforeAll
    public void setUp() {
    mockRepo = Mockito.mock(RootRepository.class);
    portfolioServiceUnderTest = new PortfolioService(mockRepo);
    }

    @AfterAll
    public void tearDown() {
        mockRepo = null;
        portfolioServiceUnderTest = null;
    }

    @Test
    void portfolioOverzichtNotNullTest() {
        assertThat(portfolioServiceUnderTest).isNotNull();
    }

    @Test
    void portfolioOverzichtTest() {
        List<Transaction> transactions= new ArrayList<>();
        Asset bitcoin =  new Asset("1","Bitcoin", "BTC","BITCOIN",47000.00);
        double exchangeRateBit = 48000.00;
        Asset ethereum = new Asset("52","Ethereum","ETH","ETH",48.00);
        double exchangeRateETH = 50.00;
        Asset nextLevelCrypto = new Asset("1000","NextLevelCrypto","NLC","NLC",0.005);
        double exchangeRateNLC = 0.005;

        //static transactionRate
        double transactionRate = 0.5;

        // Clients
        ClientDetails clientDetails = new ClientDetails("Harold", "","Stevens",
                LocalDate.parse("1973-09-25"),123456007,"info@hjstevens.nl");

        Address address = new Address("Pieter Woutersstraat",26,null,"2215MC",
                "Voorhout","");

        UserDetails userDetails = new UserDetails(  "Harold","dlorah","123",clientDetails,address);

        // Ethereum transacties:
        Transaction transaction2 = new Transaction(userDetails,userDetails,ethereum,LocalDateTime.now(),
                "Buy",2.00,50.00, transactionRate);
        Transaction transaction6 = new Transaction(userDetails,userDetails,ethereum, LocalDateTime.now(),
                "Buy",5.00,58.00,transactionRate);
        Transaction transaction7 = new Transaction(userDetails,userDetails,ethereum, LocalDateTime.now(),
                "Sell",3.00,57.00,transactionRate);

        // Bitcoin transacties:
        Transaction transaction9 = new Transaction(userDetails,userDetails,bitcoin, LocalDateTime.now(),
                "Buy",0.05,30000.00,transactionRate);
        Transaction transaction10 = new Transaction(userDetails,userDetails,bitcoin, LocalDateTime.now(),
                "Buy",0.04,35000.00,transactionRate);
        Transaction transaction1 = new Transaction(userDetails,userDetails,bitcoin, LocalDateTime.now(),
                "Buy",10.00,40000.00,transactionRate);
        Transaction transaction3 = new Transaction(userDetails,userDetails,bitcoin, LocalDateTime.now(),
                "Sell",1.00,45000.00,transactionRate);
        Transaction transaction4 =  new Transaction(userDetails,userDetails,bitcoin, LocalDateTime.now(),
                "Buy",2.00,40000.00,transactionRate);
        Transaction transaction5 =  new Transaction(userDetails,userDetails,bitcoin, LocalDateTime.now(),
                "Sell",5.00,44000.00,transactionRate);

        // NextLevelCrypto transacties:
        Transaction transaction8 =  new Transaction(userDetails,userDetails,nextLevelCrypto, LocalDateTime.now(),
                "Buy",1000.00,exchangeRateBit,transactionRate);
        Transaction transaction11 =  new Transaction(userDetails,userDetails,nextLevelCrypto, LocalDateTime.now(),
                "Sell",500.00,exchangeRateBit,transactionRate);

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        transactions.add(transaction6);
        transactions.add(transaction7);
        transactions.add(transaction8);
        transactions.add(transaction9);
        transactions.add(transaction10);
        transactions.add(transaction11);

        Mockito.when(mockRepo.findTransactionsByUSerID(1)).thenReturn(transactions);

        Mockito.when(mockRepo.findAssetById("1")).thenReturn(bitcoin);
        Mockito.when(mockRepo.findAssetById("52")).thenReturn(ethereum);
        Mockito.when(mockRepo.findAssetById("1000")).thenReturn(nextLevelCrypto);

//        assertThat(portfolioServiceUnderTest.portfolioOverzicht(1)).isEqualTo();
//        System.out.println(portfolioServiceUnderTest.getAssetsTotal(1));
        Map<Asset, Double> tempMap = portfolioServiceUnderTest.getAssetsTotal(1);
        for(Map.Entry<Asset, Double> entry :tempMap.entrySet()) {
            Asset asset = entry.getKey();
            double amount = entry.getValue();
            System.out.println(asset.getName() + " " + amount);
        }

        assertThat(tempMap.get(bitcoin)).isCloseTo(6.0, Percentage.withPercentage(5));
        assertThat(tempMap.get(ethereum)).isEqualTo(4.0);
        assertThat(tempMap.get(nextLevelCrypto)).isEqualTo(500.0);
        assertThat(portfolioServiceUnderTest.currentValueportfolio(1)).isEqualTo(286424.50);

        System.out.println(portfolioServiceUnderTest.currentValueportfolio(1));

    }

}