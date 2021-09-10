package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.blockbank.contoller.AssetController;
import com.blockbank.contoller.ClientRegistrationController;
import com.blockbank.database.domain.Asset;
import com.blockbank.database.domain.AssetDTO;
import com.blockbank.database.repository.RootRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private RootRepository rootRepository;
    private ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(AssetController.class);

    @Autowired
    public AssetService(RootRepository rootRepository, ObjectMapper mapper) {
        this.rootRepository = rootRepository;
        this.mapper = mapper;
        logger.info("New AssetService");
    }

    public String allAssetListToJson() throws JsonProcessingException {
        List<AssetDTO> assetList = rootRepository.transactionDataAssets();
        String json = mapper.writeValueAsString(assetList);
        return json;
    }

}
