package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.blockbank.database.domain.Asset;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AssetServiceTest {

    @Autowired
    AssetService assetService;


    @Test
    void assetListToJson() throws JsonProcessingException {
        //Arrange
        String actual = assetService.allAssetListToJson();
    }
}