package com.demo.bandits.reporting.infra.repository;

import com.demo.bandits.reporting.entity.WebEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WebEventRepository extends JpaRepository<WebEvent, Long>, JpaSpecificationExecutor<WebEvent> {
    List<WebEvent> findByAssetId(Long assetId);
}
