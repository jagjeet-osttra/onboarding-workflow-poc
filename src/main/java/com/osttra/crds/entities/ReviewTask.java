package com.osttra.crds.entities;

import com.osttra.crds.entities.enums.EntityType;
import com.osttra.crds.entities.enums.EventType;
import com.osttra.crds.entities.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EntityType entityType;

    private Long EntityID;
    private String EntityName;

    @Enumerated(value = EnumType.STRING)
    private EventType eventType;

    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;
    private String stagedData;
    private String submitterID;
    private LocalDateTime submittedOn;
    private String approverID;
    private LocalDateTime approvedOn;
    private String lockedBy;
    private LocalDateTime lockedOn;
    private Long instituteId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ClientStaging clientStaging;
}
