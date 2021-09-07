package com.blockbank.contoller;


import com.blockbank.database.domain.UserDetails;
import com.blockbank.service.AssetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @author Alex Shijan
 */

@RestController
public class AssetController {

    private AssetService assetService;

    private final Logger logger = LoggerFactory.getLogger(AssetController.class);

    @Autowired
    public AssetController(AssetService assetService) {
        super();
        this.assetService = assetService;
        logger.info("New AssetController");
    }

    @GetMapping("/registerDTO")
    public ResponseEntity<?> assetList() throws JsonProcessingException {
        String json = assetService.allAssetListToJson();
        if (json != null) {
            return ResponseEntity.ok(json);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
