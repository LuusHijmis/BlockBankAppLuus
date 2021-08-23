package com.blockbank.database.repository;

/**
 * @author hannahvd
 */

import com.blockbank.database.domain.Portfolio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcPortfolioDAO implements PortfolioDAO {

    private final Logger logger = LoggerFactory.getLogger(JdbcPortfolioDAO.class);
    private JdbcTemplate jdbcTemplate;

    @Override
    public Portfolio save(Portfolio portfolio) {
        return null;
    }

    @Override
    public Portfolio update(Portfolio portfolio) {
        return null;
    }

    @Override
    public Portfolio findByClientId(int clientId) {
        return null;
    }

    @Override
    public Portfolio findByAssetName(String assetName) {
        return null;
    }
}
