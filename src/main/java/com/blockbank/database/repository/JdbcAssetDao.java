package com.blockbank.database.repository;

import com.blockbank.database.domain.Asset;

import com.blockbank.database.domain.AssetDTO;
import com.blockbank.service.ExchangeRateService;
import com.blockbank.service.ExchangeRateUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
    public class JdbcAssetDao implements AssetDao {

    private final Logger logger = LoggerFactory.getLogger(com.blockbank.database.repository.AssetDao.class);
    private ExchangeRateService exchangeRateService;
    private ExchangeRateUpdateService exchangeRateUpdateService;

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public JdbcAssetDao(JdbcTemplate jdbcTemplate) {
        super();
        //this.exchangeRateService = exchangeRateService;
        //this.exchangeRateUpdateService = exchangeRateUpdateService;
        this.jdbcTemplate = jdbcTemplate;
        logger.info("New JdcbAssetDao");
    }


    private PreparedStatement updateAssetStatement(Asset asset, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "update asset set exchangeRate = ? where assetId = ? ;"
        );
        ps.setDouble(1, asset.getExchangeRate());
        ps.setString(2, asset.getAssetID());
        return ps;
    }


    public Asset updateAsset(Asset asset) {
            logger.debug("AssetDao called for updateAssets");
            jdbcTemplate.update(connection -> updateAssetStatement(asset, connection));
        return asset;
    }

    @Override
    public Asset findAssetById(String assetId) {
        logger.debug("assetDao called for findAssetById");
        List<Asset> assets = jdbcTemplate.query(
                "select * from asset where assetId = ?",  new JdbcAssetDao.AssetRowMapper(), assetId);
        if(assets.size() == 1) {
            return assets.get(0);
        }
        return null;
    }






    @Override
    public List<Asset> showAllAssets() {
        logger.debug("assetDao called for showAllAssets");
        List<Asset> assets = jdbcTemplate.query(
                "select * from asset;", new JdbcAssetDao.AssetRowMapper());
        return assets;
    }

    public List<AssetDTO> transactionDataAssets(){
        logger.debug("assetDao called for transactionDataAssets");
        List<AssetDTO> assets = jdbcTemplate.query(
                "select  symbol, name, exchangerate from asset;", new JdbcAssetDao.AssetRowMapperTransaction());
        return assets;
    }

    private static class AssetRowMapper implements RowMapper<Asset> {
        @Override

        public Asset mapRow(ResultSet resultSet, int i) throws SQLException {
            Asset result = null;
            String assetID = resultSet.getString(1);
            String symbol = resultSet.getString(2);
            String name = resultSet.getString(3);
            String description = resultSet.getString(4);
            Double exchangeRate = resultSet.getDouble(5);

            result = new Asset(assetID, name, symbol, description, exchangeRate);
            return result;
        }
    }
    private static class AssetRowMapperTransaction implements RowMapper<AssetDTO> {
        @Override

        public AssetDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            AssetDTO result = null;
            String symbol = resultSet.getString(2);
            String name = resultSet.getString(1);
            Double exchangeRate = resultSet.getDouble(3);

            result = new AssetDTO(name, symbol, exchangeRate);
            return result;
        }
    }
}