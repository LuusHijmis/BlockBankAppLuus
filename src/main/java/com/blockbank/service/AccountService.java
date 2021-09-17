package com.blockbank.service;

import com.blockbank.database.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private RootRepository rootRepository;
    private final Logger logger = LoggerFactory.getLogger(com.blockbank.service.AccountService.class);

    @Autowired
    public AccountService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }




}
