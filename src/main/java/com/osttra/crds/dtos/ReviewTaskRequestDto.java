package com.osttra.crds.dtos;

import com.osttra.crds.entities.ClientStaging;
import com.osttra.crds.entities.enums.EntityType;
import com.osttra.crds.entities.enums.EventType;
import com.osttra.crds.entities.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewTaskRequestDto {
    private Long id;
    private EntityType entityType;

    private Long EntityID;
    private String EntityName;

    private EventType eventType;

    private RequestStatus requestStatus;
    private String stagedData;
    private String submitterID;
    private LocalDateTime submittedOn;
    private String approverID;
    private LocalDateTime approvedOn;
    private String lockedBy;
    private LocalDateTime lockedOn;
    private Long instituteId;

    private ClientStaging clientStaging;
}
