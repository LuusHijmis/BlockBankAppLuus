package com.blockbank.database.repository;

import com.blockbank.database.domain.Asset;

import com.blockbank.database.domain.User;
import com.blockbank.service.ExchangeRateService;
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
import java.util.ArrayList;
import java.util.List;


@Repository
    public class JdbcAssetDao implements AssetDao {

    private final Logger logger = LoggerFactory.getLogger(com.blockbank.database.repository.JdbcAssetDao.class);
    private ExchangeRateService exchangeRateService;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAssetDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        logger.info("New JdcbAssetDao");
    }


    private PreparedStatement updateAssetStatement(Asset asset, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "update asset set exchangeRate = ?;"
        );
        ps.setDouble(1, exchangeRateService.getExchangeRate(asset.getAssetID(), "USD"));
        return ps;
    }



    @Override
    public Asset updateAssets(Asset asset) {

        logger.debug("AssetDao called for updateAssets");
        jdbcTemplate.update(connection -> updateAssetStatement(asset, connection));
        return asset;
    }

    @Override
    public Asset findAssetById(Asset asset) {
        return null;
    }

    @Override
    public List<Asset> showAllAssets() {
        logger.debug("assetDao called for showAllAssets");
        List<Asset> assets = jdbcTemplate.query(
                "select * from asset;", new JdbcAssetDao.AssetRowMapper());
        return assets;
    }

    private static class AssetRowMapper implements RowMapper<Asset> {
        @Override

        public Asset mapRow(ResultSet resultSet, int i) throws SQLException {
            Asset result = null;
            String AssetID = resultSet.getString(1);
            String name = resultSet.getString(2);
            String symbol = resultSet.getString(3);
            String description = resultSet.getString(4);
            Double exchangeRate = resultSet.getDouble(5);

            result = new Asset(AssetID, name, symbol, description, exchangeRate);
            return result;
        }
    }
}