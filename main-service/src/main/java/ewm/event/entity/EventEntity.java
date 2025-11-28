package ewm.event.entity;


import ewm.categories.entity.CategoryEntity;
import ewm.event.domain.State;
import ewm.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "annotation", nullable = false)
    private String annotation;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @JoinColumn(name = "confirmedRequests")
    private Integer confirmedRequests;

    @Column(name = "createdOn", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "eventDate", nullable = false)
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "initiator_id", nullable = false)
    private UserEntity initiator;

    @Embedded
    @Column(name = "location", nullable = false)
    private LocationEntity location;

    @Column(name = "paid", nullable = false)
    private Boolean paid;

    @Column(name = "participantLimit", nullable = false)
    private Integer participantLimit;

    @Column(name = "publishedOn")
    private LocalDateTime publishedOn;

    @Column(name = "requestModeration",nullable = false)
    private Boolean requestModeration;

    @Column(name = "requestModeration", nullable = false)
    private State state;

    @Column(name = "title", nullable = false)
    private String title;

}
