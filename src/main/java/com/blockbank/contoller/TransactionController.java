package com.blockbank.contoller;

import com.blockbank.database.domain.Transaction;
import com.blockbank.database.domain.TransactionDTO;
import com.blockbank.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class TransactionController {

   private TransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
        logger.info("New TransactionController");
    }
    @PostMapping("/transaction")
    public ResponseEntity<?> storeTransaction(@RequestBody TransactionDTO transactionDTO) {
       Transaction transaction = transactionService.storeTransaction(transactionDTO);
        if (transaction != null) {
            URI uri = URI.create(String.valueOf(transaction.getTransactionID()));
            // front-end related
            return ResponseEntity.created(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/transactionhistory")
    public ResponseEntity<?> getTransactions(@RequestBody int userID) throws JsonProcessingException {
        String transactionsJson = transactionService.getTransactionsJson(userID);
        if (transactionsJson != null) {
            URI uri = URI.create("Lijst");
            // front-end related
            return ResponseEntity.created(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
