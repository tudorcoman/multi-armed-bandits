package ro.unibuc.coman.licenta.reporting.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.unibuc.coman.licenta.reporting.entity.WebEvent;

import java.util.List;

public interface WebEventRepository extends JpaRepository<WebEvent, Long>, JpaSpecificationExecutor<WebEvent> {
    List<WebEvent> findByAssetId(Long assetId);
}
