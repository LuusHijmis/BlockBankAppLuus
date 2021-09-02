package com.blockbank.database.repository;

import com.blockbank.database.domain.Asset;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcAssetDaoTest {

    AssetDao assetDaoUnderTest;

    private final Logger logger = LoggerFactory.getLogger(JdbcAssetDaoTest.class);

    @Autowired
    public JdbcAssetDaoTest(AssetDao dao) {
        super();
        this.assetDaoUnderTest = dao;
        logger.info("New JdbcAssetDaoTest");
    }

    @Test
    public void assetDaoNotNull() {
        assertThat(assetDaoUnderTest).isNotNull();
    }

    @Test
    public void showAllAssets(){
        System.out.println(assetDaoUnderTest.showAllAssets());
    }

}