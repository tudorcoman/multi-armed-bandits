package com.demo.bandits.reporting.infra.repository;

import com.demo.bandits.reporting.entity.Treatment;
import com.demo.bandits.reporting.entity.WebAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long>, JpaSpecificationExecutor<Treatment> {
    List<Treatment> findAllByWebAsset(WebAsset webAsset);
}
