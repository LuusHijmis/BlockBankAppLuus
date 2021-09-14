package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.blockbank.contoller.AssetController;
import com.blockbank.database.domain.Asset;
import com.blockbank.database.domain.Transaction;
import com.blockbank.database.domain.TransactionDTO;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankTransactionGenerator {

    private final Logger logger = LoggerFactory.getLogger(BankTransactionGenerator.class);
    RootRepository rootRepository;

    @Autowired
    public BankTransactionGenerator(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
        logger.info("New BankTransactionGenerator");
    }

    public void generateStaringTransactions() {
        String admin = "Admin";
        String transactionSort = "Buy";
        LocalDateTime time = LocalDateTime.now();
        Double transactionFee = 0.00;
        int opposingUserId = 9999;
        List<Asset> assetList = rootRepository.showAllAssets();
        if(adminCheck(admin)==true) {
            UserDetails adminstrator= rootRepository.findUserByUsername(admin);
            for(Asset asset : assetList) {
                rootRepository.saveTransaction(new TransactionDTO(adminstrator.getUserID(), time,
                        transactionSort, randomNumberGenerator() ,asset.getExchangeRate(), transactionFee,
                        opposingUserId, asset.getAssetID()));
            }
        } else {
            System.out.println("Bank does not exist");
        }
    }

    public boolean adminCheck(String username) {
        UserDetails admin= rootRepository.findUserByUsername(username);
        if(admin.equals(null)) {
            return false;
        } else {
            return true;
        }
    }

    public double randomNumberGenerator() {
        int min = 1000;
        int max = 5000;
        int result = (int)(Math.random()*(max - min+1)+min);

        return result;
    }
}
