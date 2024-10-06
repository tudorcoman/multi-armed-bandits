package ro.unibuc.coman.licenta.reporting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "web_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class WebEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty
    private Timestamp timestamp;

    @Column
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private EventType type;

    @ManyToOne(targetEntity = WebAsset.class)
    @JoinColumn(name="asset_id")
    private WebAsset asset;

    @ManyToOne(targetEntity = Treatment.class)
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;
}
