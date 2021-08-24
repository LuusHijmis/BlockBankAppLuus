package com.blockbank.database.repository;

/**
 * @author hannahvd
 */

import com.blockbank.database.domain.Portfolio;

public interface PortfolioDAO {

    Portfolio save(Portfolio portfolio);
    Portfolio update(Portfolio portfolio);
    Portfolio findByClientId(int clientId);
    Portfolio findByAssetName(String assetName);
}
