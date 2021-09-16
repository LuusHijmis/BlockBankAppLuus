package com.blockbank.database.repository;

import com.blockbank.database.domain.Transaction;
import com.blockbank.database.domain.TransactionDTO;

import java.util.List;

public interface TransactionDao {

    Transaction save (Transaction transaction);
    Transaction delete(Transaction transaction);
    List<TransactionDTO> findTransactionByUserId(int userID);
    List<TransactionDTO> findTransactionByOpposingUserId(int userID);
    TransactionDTO findTransactionByID(int id);
}
