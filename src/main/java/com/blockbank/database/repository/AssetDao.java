package com.blockbank.database.repository;

import com.blockbank.database.domain.Asset;

import java.util.List;

public interface AssetDao {
    Asset updateAssets(Asset asset);
    Asset findAssetById(Asset asset);
    List<Asset> showAllAssets();

}
