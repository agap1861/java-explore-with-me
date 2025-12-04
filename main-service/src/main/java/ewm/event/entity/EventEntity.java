package ewm.event.entity;


import ewm.categories.entity.CategoryEntity;
import ewm.event.domain.EventState;
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

    @Column(name = "annotation", nullable = false, length = 2000)
    private String annotation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @JoinColumn(name = "confirmedRequests")
    private Integer confirmedRequests;

    @Column(name = "createdOn", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "description", nullable = false, length = 7000)
    private String description;

    @Column(name = "eventDate", nullable = false)
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "initiator_id", nullable = false)
    private UserEntity initiator;

    @Embedded
    private LocationEntity location;

    @Column(name = "paid", nullable = false)
    private Boolean paid;

    @Column(name = "participantLimit", nullable = false)
    private Integer participantLimit;

    @Column(name = "publishedOn")
    private LocalDateTime publishedOn;

    @Column(name = "requestModeration", nullable = false)
    private Boolean requestModeration;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventState state;

    @Column(name = "title", nullable = false, length = 120)
    private String title;

}
