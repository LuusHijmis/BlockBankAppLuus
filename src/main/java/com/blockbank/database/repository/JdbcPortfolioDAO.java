package com.blockbank.database.repository;

/**
 * @author hannahvd
 */

import com.blockbank.database.domain.Portfolio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JdbcPortfolioDAO implements PortfolioDAO {

    private final Logger logger = LoggerFactory.getLogger(JdbcPortfolioDAO.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPortfolioDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        logger.info("New JdbcPortfolioDAO");
    }

    private PreparedStatement insertPortfolioStatement(Portfolio portfolio, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO portfolio (clientID, asset(temp), aantal) values (?, ?, ?)"
        );
        statement.setInt(1, portfolio.getClientId());

//        statement.setString(2, portfolio.getAssetList()
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        e -> e.getKey().toString()
//                ))
//                .toString());           //dit is bullshit
//
//        statement.setInt(3, portfolio.getAssetList()
//                .entrySet().stream().collect(Collectors.toMap(
//                        Map.Entry::getValue
//                )));
        return statement;
    }

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
