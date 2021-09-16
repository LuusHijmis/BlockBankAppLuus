package com.blockbank.contoller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blockbank.database.domain.PortfolioDTO;
import com.blockbank.database.domain.Transaction;
import com.blockbank.database.domain.TransactionDTO;
import com.blockbank.service.TokenService;
import com.blockbank.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TransactionController {

   private TransactionService transactionService;
   private TokenService tokenService;
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(TransactionService transactionService, TokenService tokenService) {
        this.transactionService = transactionService;
        this.tokenService = tokenService;
        logger.info("New TransactionController");
    }
    @PostMapping("/transaction")
    public ResponseEntity<?> storeTransaction(@RequestHeader (name="Authorization") String token,
                                              @RequestHeader (name="transactionSort") String transactionSort,
                                              @RequestHeader (name="amount") double amount,
                                              @RequestHeader (name="opposingUserID") int oppossingUserID,
                                              @RequestHeader (name="assetID") String assetID) {
        if(tokenService.verifyToken(token)) {
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getClaim("username").asString();
            int userID = transactionService.rootRepository.findUserByUsername(username).getUserID();
            double exchangeRate = transactionService.rootRepository.findAssetById(assetID).getExchangeRate();
            double transactionCost= 0.5;
            TransactionDTO transactionDTO = new TransactionDTO(userID, LocalDateTime.now(),transactionSort,amount,
                    exchangeRate,transactionCost,oppossingUserID,assetID);
            transactionService.storeTransaction(transactionDTO);

            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(403).build();
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
