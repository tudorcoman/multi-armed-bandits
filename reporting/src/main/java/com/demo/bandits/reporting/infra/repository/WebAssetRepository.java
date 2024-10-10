package com.demo.bandits.reporting.infra.repository;

import com.demo.bandits.reporting.entity.WebAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WebAssetRepository extends JpaRepository<WebAsset, Long>, JpaSpecificationExecutor<WebAsset> {
}
