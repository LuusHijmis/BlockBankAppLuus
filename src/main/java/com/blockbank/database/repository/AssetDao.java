package com.blockbank.database.repository;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.domain.AssetDTO;

import java.util.List;

public interface AssetDao {
    Asset updateAsset(Asset asset);
    Asset findAssetById(String assetId);
    List<Asset> showAllAssets();
    List<AssetDTO> transactionDataAssets();

}
