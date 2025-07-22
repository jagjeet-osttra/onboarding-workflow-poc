package com.osttra.crds.services;

import com.osttra.crds.dtos.StatusTrackerResponseDto;
import com.osttra.crds.entities.WorkflowStatusTracker;

public interface WorkflowStatusTrackerService {
    WorkflowStatusTracker getDetailsByProcessId(String processInstanceId);
    void updateTaskIdByProcessId(String processInstanceId,String taskId);
    void updateClientIdByProcessId(String processInstanceId,Long clientId);
    void updateClientStagingIdByProcessId(String processInstanceId,Long clientStagingId);
    StatusTrackerResponseDto createProcessId(String processId);
    void createClientFromProcessId(String processId);
    WorkflowStatusTracker getWorkflowStatusTrackerByTaskId(String taskId);
    void updateWorkflowStatusTrackerById(WorkflowStatusTracker workflowStatusTracker);
    void updateReviewTaskId(String processInstanceId,Long reviewTaskId);
}
