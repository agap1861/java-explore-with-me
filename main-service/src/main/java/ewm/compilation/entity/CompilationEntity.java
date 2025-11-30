package ewm.compilation.entity;

import ewm.event.entity.EventEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "compilations")
public class CompilationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "compilations_events",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<EventEntity> events;

    @Column(name = "pinned",nullable = false)
    private Boolean pinned;

    @Column(name = "title", nullable = false)
    private String title;

}
