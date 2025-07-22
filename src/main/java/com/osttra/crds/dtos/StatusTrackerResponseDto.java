package com.osttra.crds.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusTrackerResponseDto {

    private Long id;
    private String processInstanceId;
    private String taskId;
    private Long reviewTaskId;
    private Long clientId;
    private Long clientStagingId;
}
