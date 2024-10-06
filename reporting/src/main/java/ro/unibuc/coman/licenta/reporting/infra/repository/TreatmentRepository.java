package ro.unibuc.coman.licenta.reporting.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.unibuc.coman.licenta.reporting.entity.Treatment;
import ro.unibuc.coman.licenta.reporting.entity.WebAsset;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long>, JpaSpecificationExecutor<Treatment> {
    List<Treatment> findAllByWebAsset(WebAsset webAsset);
}
