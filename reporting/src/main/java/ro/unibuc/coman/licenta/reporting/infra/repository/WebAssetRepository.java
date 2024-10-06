package ro.unibuc.coman.licenta.reporting.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.unibuc.coman.licenta.reporting.entity.WebAsset;

public interface WebAssetRepository extends JpaRepository<WebAsset, Long>, JpaSpecificationExecutor<WebAsset> {
}
