package com.blockbank.service;

/**
 * @author Alex Shijan
 */


import com.blockbank.database.domain.Asset;
import com.blockbank.database.domain.Transaction;
import com.blockbank.database.domain.TransactionDTO;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

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
        int opposingUserId = 2;
        UserDetails adminstrator= rootRepository.findUserByUsername(admin);
        List<Asset> assetList = rootRepository.showAllAssets();
        List<Transaction> transactions = rootRepository.findTransactionsByUSerID(adminstrator.getUserID());
        if(adminCheck(admin)==true && transactions == null) {
            for(Asset asset : assetList) {
                rootRepository.saveTransaction(new TransactionDTO(adminstrator.getUserID(), time,
                        transactionSort, randomNumberGenerator() ,asset.getExchangeRate(), transactionFee,
                        opposingUserId, asset.getAssetID()));
            }
        } else {
            System.out.println("Bank does not exist or it already has starting transactions.");
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
