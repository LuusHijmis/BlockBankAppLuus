package com.blockbank.database.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

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
    void assetDaoNotNull() {
        assertThat(assetDaoUnderTest).isNotNull();
    }

    @Test
    void showAllAssets(){
        System.out.println(assetDaoUnderTest.showAllAssets());
    }

    @Test
    void transactionDataAssets(){
        System.out.println(assetDaoUnderTest.transactionDataAssets());
    }

    @Test
    void findAssetById(){
        System.out.println(assetDaoUnderTest.findAssetById("1"));
    }

    @Test
    void updateAssets(){
        var original = assetDaoUnderTest.findAssetById("1");
        //var update = new Asset("1");
        //update.setExchangeRate(2);
        var actual = assetDaoUnderTest.updateAsset(original);
        var found = assetDaoUnderTest.findAssetById("1");
        System.out.println("actual "+ actual);
        System.out.println("found "+ found);
        //assertThat(actual).isEqualTo(found);
        //assertThat(found).isNotEqualTo(original);
    }


}